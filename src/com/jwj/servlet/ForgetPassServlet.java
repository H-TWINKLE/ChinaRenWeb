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
 * Servlet implementation class ForgetPassServlet
 */
@WebServlet("/ForgetPassServlet")
public class ForgetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		
		PrintWriter pw = response.getWriter();
		String forgetpassadmin = request.getParameter("forgetpassadmin");
		
		if(forgetpassadmin!=null&&!forgetpassadmin.equals("")) {
			try {
				if(UserFactory.getInstance().find(forgetpassadmin)) {
					try {						
							String code = CommUtil.getInstance().productCode(30);	
							if(UserFactory.getInstance().updateUserCode(forgetpassadmin, code)) {
								try {
									String url = CommUtil.getInstance().isServicePath("CheckPassCodeServlet") + code;
									new EmilSend().send_mail(forgetpassadmin, CommUtil.getInstance().toEmilText(forgetpassadmin, url).toString(),
											"修改密码");								
								} catch (MessagingException e) {									
									e.printStackTrace();
									pw.println("内部异常");
									pw.flush();
									pw.close();
									return;
								}
								pw.write("邮件发送成功，请前往邮箱"+forgetpassadmin+"修改密码！");
								pw.flush();
								pw.close();
								return;}
					} catch (Exception e) {
						e.printStackTrace();
						pw.println("内部异常");
						pw.flush();
						pw.close();
						return;
					}
				}else {
					pw.write("该用户尚未注册！");
					pw.flush();
					pw.close();
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			pw.write("请输入邮箱！");
			pw.flush();
			pw.close();
			return;			
		}
	}

}
