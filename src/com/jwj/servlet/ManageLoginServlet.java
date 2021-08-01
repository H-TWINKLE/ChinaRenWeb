package com.jwj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.ManageFactory;
import com.jwj.entity.Manage;
/**
 * Servlet implementation class ManageLoginServlet
 */
@WebServlet("/ManageLoginServlet")
public class ManageLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageLoginServlet() {
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

		
		String admins = request.getParameter("admins");
		String pass = request.getParameter("password");
		
		
		if(admins==null||admins.equals("")) {
			pw.println("«Î ‰»Î” œ‰£°");
			pw.flush();
			pw.close();
			return;
		} else if(pass ==null||pass.equals("")) {
			pw.println("«Î ‰»Î√‹¬Î£°");
			pw.flush();
			pw.close();
			return;
		}else {
			Manage user = null ;
			try {	
					 user = ManageFactory.getInstance().doAdminLogin(admins, pass);
					 
					 if(user!=null) {
							request.getSession().setAttribute("admin", user);
							pw.write("µ«¬Ω≥…π¶!");
							pw.flush();
							pw.close();
							return;
						}else {
							pw.println("” œ‰ªÚ’ﬂ√‹¬Î¥ÌŒÛ£°");
							pw.flush();
							pw.close();
							return;
						}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
				
	}
}
