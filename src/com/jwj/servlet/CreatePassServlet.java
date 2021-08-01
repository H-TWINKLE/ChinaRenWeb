package com.jwj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.UserFactory;

/**
 * Servlet implementation class CreatePassServlet
 */
@WebServlet("/CreatePassServlet")
public class CreatePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePassServlet() {
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
			pw.println("����������");
			pw.flush();
			pw.close();
			return;
		}
		else if(rpass==null||pass.equals("")) {
			pw.println("���ٴ���������");
			pw.flush();
			pw.close();
			return;
		}
		else if(!rpass.equals(pass)) {
			pw.println("�������벻ͬ������������");
			pw.flush();
			pw.close();
			return;
		}
		else if(rpass.length()<6) {
			pw.println("�������6λ������������");
			pw.flush();
			pw.close();
			return;
		}
		else {
			String admins = (String) request.getSession().getAttribute("admins");
			if(admins==null||admins.equals("")) {
				pw.write("false");
				pw.flush();
				pw.close();
				return;
			}else {
				try {
					if(UserFactory.getInstance().find(admins)) {
						pw.println("���û��Ѿ�ע�ᣬ���½��");
						pw.flush();
						pw.close();
						return;
					}else {
						try {
							if(UserFactory.getInstance().registerUser(admins, rpass)) {								
								pw.write("true");
								pw.flush();
								pw.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}