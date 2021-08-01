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
 * Servlet implementation class AsAdminServlet
 */
@WebServlet("/AsAdminServlet")
public class AsAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AsAdminServlet() {
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

		User user = (User) request.getSession().getAttribute("admin");

		try {

			if (UserFactory.getInstance().isAdminExist(user.getClazz().getId())) {
				pw.write("����Ա�Ѿ����ڣ������ܹ����룡");
				pw.flush();
				pw.close();
				return;
			}

			int id = UserFactory.getInstance().getBestClazzAdmin(user.getClazz().getId());
			System.out.println("��ѹ���T:" + id);

			if (user.getId() == id) {
				if (UserFactory.getInstance().setUserPointAdd(user.getId(), 1, "isadmin")) {
					user.setIsadmin(1);
					request.getSession().setAttribute("admin", user);
					pw.write("��Ϊ����Ա�ɹ���");
					pw.flush();
					pw.close();
					return;
				}

			} else {
				pw.write("���ľ���ֵ�����Գ�Ϊ����Ա��");
				pw.flush();
				pw.close();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
