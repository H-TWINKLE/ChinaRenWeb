package com.jwj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.MessFactory;
import com.jwj.entity.User;

/**
 * Servlet implementation class ToShareServlet
 */
@WebServlet("/ToShareServlet")
public class ToShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToShareServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw = response.getWriter();

		String rid = request.getParameter("rid");
		String what = request.getParameter("what");
		
		int f = Integer.parseInt(request.getParameter("f"));

		User user = (User) request.getSession().getAttribute("admin");

		if (what == null || what.equals("")) {
			pw.write("ÇëÊäÈëÄÚÈÝ£¡");
			pw.flush();
			pw.close();
			return;
		} else {
			try {
				if(f==1) {
					if (MessFactory.getInstance().doInsert("usermess",what, rid, String.valueOf(user.getId()))) {
						pw.write("ÁôÑÔ³É¹¦£¡");
						pw.flush();
						pw.close();
						return;
					} else {
						pw.write("ÁôÑÔÊ§°Ü£¡");
						pw.flush();
						pw.close();
						return;
					}
				}else if(f==2) {
					if (MessFactory.getInstance().doInsert("clazzmess",what,String.valueOf(user.getId()),rid)) {
						pw.write("ÁôÑÔ³É¹¦£¡");
						pw.flush();
						pw.close();
						return;
					} else {
						pw.write("ÁôÑÔÊ§°Ü£¡");
						pw.flush();
						pw.close();
						return;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
