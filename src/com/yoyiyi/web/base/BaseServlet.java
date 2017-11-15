package com.yoyiyi.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String methodName = request.getParameter("method");
			if (methodName != null && "".equals(methodName)) {
				Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,
						HttpServletResponse.class);
				method.invoke(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
