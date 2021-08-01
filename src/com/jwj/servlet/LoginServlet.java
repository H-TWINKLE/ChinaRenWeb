package com.jwj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.UserFactory;
import com.jwj.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw = response.getWriter();

		
		String admins = request.getParameter("admins");
		String pass = request.getParameter("password");
					
		if(admins==null||admins.equals("")) {
			pw.println("���������䣡");
			pw.flush();
			pw.close();
			return;
		} else if(pass ==null||pass.equals("")) {
			pw.println("���������룡");
			pw.flush();
			pw.close();
			return;
		}else {
			User user = null ;
			try {
				if(UserFactory.getInstance().find(admins)) {
					 user = UserFactory.getInstance().login(admins, pass);
				}else {
					pw.println("��������δע�ᣡ");
					pw.flush();
					pw.close();
					return;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(user!=null) {				
				request.getSession().setAttribute("admin", user);
				pw.write("��½�ɹ�!");				
				pw.flush();
				pw.close();
				try {
					System.out.println("����ֵ:"+user.getPoint());
					if(UserFactory.getInstance().setUserPointAdd(user.getId(), user.getPoint()+1, "point")) {
						System.out.println("�������ӳɹ���");
					}else {
						System.out.println("��������ʧ�ܣ�");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}else {
				pw.println("��������������");
				pw.flush();
				pw.close();
				return;
			}
		}
				
	}

}
