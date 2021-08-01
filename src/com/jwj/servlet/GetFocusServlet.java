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
 * Servlet implementation class GetFocusServlet
 */
@WebServlet("/GetFocusServlet")
public class GetFocusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFocusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw = response.getWriter();


		User user = (User)request.getSession().getAttribute("admin");
		
		
		
		int u_id=Integer.parseInt(request.getParameter("id"));
		int flag = Integer.parseInt(request.getParameter("flag"));
		
		//System.out.println("U_id:"+u_id+"  flag:"+flag);
		
		if(u_id==0&&flag==0) {
			pw.write("操作失败！！");
			pw.flush();
			pw.close();
			return;
		}
		
		if(flag==1) {
			try {
				if(UserFactory.getInstance().getFocus(u_id, user.getId())) {
					pw.write("关注成功！");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("关注失败！");
					pw.flush();
					pw.close();
					return;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else if(flag==2) {
			try {
				if(UserFactory.getInstance().JoinClazz(u_id, user.getId())) {
					
					user = UserFactory.getInstance().getUser(user.getId());
					
					request.getSession().setAttribute("admin", user);
					
					pw.write("操作成功！");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("操作失败！");
					pw.flush();
					pw.close();
					return;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		
		
	}

}
