package com.jwj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.UserFactory;
import com.jwj.entity.Clazz;
import com.jwj.entity.User;
import com.jwj.entity.UserMore;

/**
 * Servlet implementation class SetSchoolServlet
 */
@WebServlet("/SetCollegeServlet")
public class SetCollegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetCollegeServlet() {
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

		String name = request.getParameter("name");

		String nickname = request.getParameter("nickname");

		String selectsex = request.getParameter("selectsex");

		String selectyear = request.getParameter("selectyear");
		
		String selectgraden = request.getParameter("selectgraden");

		String selectclazz = request.getParameter("selectclazz");

		String selectmajor = request.getParameter("selectmajor");

		String selectcollege = request.getParameter("selectcollege");

		String tel = request.getParameter("tel");

		String selectdate = request.getParameter("selectdate");

		String home = request.getParameter("home");

		String live = request.getParameter("live");

		String admins = (String) request.getSession().getAttribute("admins");

		int clazz_id = 0;
		int usermore_id = 0;
		try {
			clazz_id = UserFactory.getInstance()
					.findCollege(new Clazz(selectclazz, selectgraden, selectmajor, selectcollege));
		} catch (Exception e) {
			e.printStackTrace();
		}

		 System.out.println(clazz_id+" "+usermore_id);

		if (clazz_id == 0) {
			try {
				if (UserFactory.getInstance()
						.setCollege(new Clazz(selectclazz, selectgraden, selectmajor, selectcollege))) {

					clazz_id = UserFactory.getInstance()
							.findCollege(new Clazz(selectclazz, selectgraden, selectmajor, selectcollege));
				} else {
					pw.write("内部异常");
					pw.flush();
					pw.close();
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		System.out.println(clazz_id+" "+usermore_id);
		
		if (clazz_id == 0) {

			pw.write("内部异常");
			pw.flush();
			pw.close();
			return;
		} else {
			
			try {
				usermore_id = UserFactory.getInstance()
						.findUserMore(new UserMore(selectyear, tel, selectdate, home,live));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(usermore_id==0) {
				try {
					if (UserFactory.getInstance()
							.setUserMore(new UserMore(selectyear, tel, selectdate, home,live) )) {

						usermore_id = UserFactory.getInstance()
								.findUserMore(new UserMore(selectyear, tel, selectdate, home,live));
					} else {
						pw.write("内部异常");
						pw.flush();
						pw.close();
						return;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(clazz_id+" "+usermore_id);

		try {

			if (UserFactory.getInstance().updateUserRegis(new User(admins,name,nickname,selectsex,new Clazz(clazz_id),new UserMore(usermore_id)))) {
				pw.write("true");
				pw.flush();
				pw.close();
				return;
			}else {
				pw.write("内部异常");
				pw.flush();
				pw.close();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
