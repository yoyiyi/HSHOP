package com.yoyiyi.web.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public EncodingFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		try {
			request.setCharacterEncoding("utf-8");
			HttpServletRequest encondingReq = (HttpServletRequest) Proxy.newProxyInstance(
					req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
						public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
							if ("getParameter".equals(arg1.getName())) {
								String value = req.getParameter(arg2[0].toString());
								return new String(value.getBytes("iso-8859-1"), "utf-8");
								
								
								
							}
							return arg1.invoke(req, arg2);
						}
					});
			chain.doFilter(encondingReq, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// chain.doFilter(new EncondingRequest((HttpServletRequest) request), response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/*
	 * class EncondingRequest extends HttpServletRequestWrapper {
	 * 
	 * private HttpServletRequest request;
	 * 
	 * public EncondingRequest(HttpServletRequest request) { super(request);
	 * this.request = request; }
	 * 
	 * @Override public String getParameter(String name) { String parameter =
	 * request.getParameter(name); try { parameter = new
	 * String(parameter.getBytes("iso8859-1"), "UTF-8"); } catch (Exception e) {
	 * e.printStackTrace(); } return parameter; }
	 */

}
