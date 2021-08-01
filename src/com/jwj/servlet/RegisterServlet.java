package com.jwj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.UserFactory;
import com.jwj.mail.EmilSend;
import com.jwj.util.CommUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw = response.getWriter();

		String admins = request.getParameter("admins");
		if (admins != null&&!admins.equals("")) {
			try {
				if (!UserFactory.getInstance().find(admins)) {
					String code = CommUtil.getInstance().productCode(30);
					if (UserFactory.getInstance().register(admins, code)) {
						pw.println("成功发送邮件到邮箱：" + admins + ",请前往激活账户！");
						pw.flush();
						try {
							String url = CommUtil.getInstance().isServicePath("CheckCodeServlet") + code;
							new EmilSend().send_mail(admins, CommUtil.getInstance().toEmilText(admins, url).toString(),
									"激活账户");
							//System.out.println("发送成功：");
						} catch (MessagingException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
							pw.println("内部异常");
							pw.flush();
							pw.close();
							return;
						}
					} else {
						pw.println("内部异常");
						pw.flush();
						pw.close();
						return;
					}
				} else {
					pw.println("该用户已经注册，请登陆！");
					pw.flush();
					pw.close();
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
				pw.println("内部异常");
				pw.flush();
				pw.close();
				return;
			}
		} else {
			pw.println("请输入邮箱！");
			pw.flush();
			pw.close();
			return;
		}				
	}

}
