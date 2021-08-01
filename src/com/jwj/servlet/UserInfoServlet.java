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
import com.jwj.entity.UserMore;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw = response.getWriter();


		User user = (User)request.getSession().getAttribute("admin");

        if(user==null) {
        	pw.write("ÐÞ¸ÄÊ§°Ü£¡");
			pw.flush();
			pw.close();
        	return;
        }
        
       // System.out.println(user.getUsermore().getBirth()+" "+user.getUsermore().getYear());
        
        String tel = request.getParameter("tel");
        String auto = request.getParameter("auto");
        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String home = request.getParameter("home");
        String live = request.getParameter("live");
        String year = request.getParameter("year");
        String birth = request.getParameter("birth");
        
        if(tel!=null||auto!=null||name!=null||nickname!=null||home!=null||live!=null) {
        	try {
        		
        		user.setName(name);
        		user.setNickname(nickname);
        		user.setAuto(auto);
        		user.setUsermore(new UserMore(year,tel,birth,home,live));
        		
    			if (UserFactory.getInstance().updateUser(user)) {
    				
    				request.getSession().setAttribute("admin", user);
    				
    				pw.write("ÐÞ¸Ä³É¹¦£¡");
    				pw.flush();
    				pw.close();
    				return;
    			} else {
    				pw.write("ÐÞ¸ÄÊ§°Ü£¡");
    				pw.flush();
    				pw.close();
    				return;
    			}
    		} catch (Exception e) {
    			// TODO: handle exception
    		}
        }else {
        	pw.write("ÐÞ¸ÄÊ§°Ü£¡");
			pw.flush();
			pw.close();
        	return;
        }
		

		

	}

}
