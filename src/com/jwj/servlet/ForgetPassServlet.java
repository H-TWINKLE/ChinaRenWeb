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
											"�޸�����");								
								} catch (MessagingException e) {									
									e.printStackTrace();
									pw.println("�ڲ��쳣");
									pw.flush();
									pw.close();
									return;
								}
								pw.write("�ʼ����ͳɹ�����ǰ������"+forgetpassadmin+"�޸����룡");
								pw.flush();
								pw.close();
								return;}
					} catch (Exception e) {
						e.printStackTrace();
						pw.println("�ڲ��쳣");
						pw.flush();
						pw.close();
						return;
					}
				}else {
					pw.write("���û���δע�ᣡ");
					pw.flush();
					pw.close();
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			pw.write("���������䣡");
			pw.flush();
			pw.close();
			return;			
		}
	}

}
