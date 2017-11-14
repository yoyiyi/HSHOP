package com.yoyiyi.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import com.mchange.v2.beans.BeansUtils;
import com.yoyiyi.domain.User;
import com.yoyiyi.service.UserService;
import com.yoyiyi.utils.CommonsUtils;
import com.yoyiyi.utils.MailUtils;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		try {
			// Date 装换成String
			ConvertUtils.register(new Converter() {
				public Object convert(Class arg0, Object arg1) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date parse = null;
					try {
						parse = format.parse(arg1.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return parse;
				}
			}, Date.class);
			BeanUtils.populate(user, parameterMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 设置uuid
		user.setUid(CommonsUtils.getUUID());
		// 设置电话
		user.setTelephone(null);
		// 设置激活状态
		user.setState(0);
		// 设置激活码
		String activeCode = CommonsUtils.getUUID();
		// 设置激活码 设置为uuid
		user.setCode(activeCode);
		// 注册
		UserService service = new UserService();
		boolean isRegister = service.register(user);
		if (isRegister) {// 注册成功
			// 发送激活邮件
			String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户" + "<a href='http://localhost:8080/HSHOP/active?activeCode="
					+ activeCode + "'>" + "http://localhost:8080/HSHOP/active?activeCode=" + activeCode + "</a>";
			try {
				// 发送激活邮件
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			// 跳转到成功界面
			response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");
		} else {// 注册失败
			// 跳转到失败界面
			response.sendRedirect(request.getContextPath() + "/registerFail.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
