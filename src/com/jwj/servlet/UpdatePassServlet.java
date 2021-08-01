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
 * Servlet implementation class UpdatePassServlet
 */
@WebServlet("/UpdatePassServlet")
public class UpdatePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassServlet() {
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

		String pass = request.getParameter("pass");
		String rpass = request.getParameter("rpass");
		
		if(pass==null||pass.equals("")) {
			pw.println("请输入密码");
			pw.flush();
			pw.close();
			return;
		}
		else if(rpass==null||pass.equals("")) {
			pw.println("请再次输入密码");
			pw.flush();
			pw.close();
			return;
		}
		else if(!rpass.equals(pass)) {
			pw.println("两次密码不同，请重新输入");
			pw.flush();
			pw.close();
			return;
		}
		else if(rpass.length()<6) {
			pw.println("密码最低6位，请重新输入");
			pw.flush();
			pw.close();
			return;
		}
		else {
			User admin = (User) request.getSession().getAttribute("admin");			
			if(admin==null) {
				pw.write("false");
				pw.flush();
				pw.close();
				return;
			}else {				
				try {
					if(UserFactory.getInstance().find(admin.getAdmin())) {
						try {
							if(UserFactory.getInstance().update(admin.getAdmin(), rpass)) {								
								pw.write("true");
								pw.flush();
								pw.close();
								return;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else {						
						pw.write("该用户尚未注册！");
						pw.flush();
						pw.close();												
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	}

