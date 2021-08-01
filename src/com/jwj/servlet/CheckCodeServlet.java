package com.jwj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.UserFactory;
import com.jwj.init.InitString;

/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String code = request.getParameter("code");
		if(code==null) {
			//System.out.println("null");
			response.sendRedirect(InitString.error_page);
		}else {
			
			try {			 
				String admins =  UserFactory.getInstance().findRegister(code);
				if(admins.equals("")||admins==null) {
					response.sendRedirect(InitString.error_page);	
				}else {					
					request.getSession().setAttribute("admins", admins);		
					response.sendRedirect(InitString.registerUser_page);
					return;						
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}

}
