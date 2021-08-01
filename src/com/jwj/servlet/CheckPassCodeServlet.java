package com.jwj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.UserFactory;
import com.jwj.entity.User;
import com.jwj.init.InitString;

/**
 * Servlet implementation class CheckPassCodeServlet
 */
@WebServlet("/CheckPassCodeServlet")
public class CheckPassCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPassCodeServlet() {
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

		String code = request.getParameter("code");
		
		if(code==null||code.equals("")) {			
			response.sendRedirect(InitString.error_page);
		}else {				
			User admin = new User();
			try {			 
				admin =  UserFactory.getInstance().findUserCode(code);
				if(admin.getAdmin()==null||admin.getAdmin().equals("")) {
					System.out.println("null");
					response.sendRedirect(InitString.error_page);	
				}else {
					System.out.println(admin);
					request.getSession().setAttribute("admin", admin);		
					response.sendRedirect(InitString.updatePass_page);
					return;						
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}

	}


