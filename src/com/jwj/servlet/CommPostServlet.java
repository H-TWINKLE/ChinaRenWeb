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
import com.jwj.entity.UserComplaint;

/**
 * Servlet implementation class CommPostServlet
 */
@WebServlet("/CommPostServlet")
public class CommPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommPostServlet() {
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

		int f = Integer.parseInt(request.getParameter("f"));

		String textArea = (String) request.getParameter("textArea");

		String radio = (String) request.getParameter("radio");

		User user = (User) request.getSession().getAttribute("admin");

		// System.out.println(" f:"+f+" textArea:"+textArea+" radio:"+radio+"
		// userid:"+user.getId());

		if (f == 1) {
			try {
				if (UserFactory.getInstance().setUserComplaint(user.getId(), new UserComplaint(textArea, radio))) {
					pw.write("发布成功！");
					pw.flush();
					pw.close();
					return;
				} else {
					pw.write("发布失败！");
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
