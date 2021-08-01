package com.jwj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.ManageFactory;
import com.jwj.entity.Clazz;

/**
 * Servlet implementation class ManageUpServlet
 */
@WebServlet("/ManageUpServlet")
public class ManageUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageUpServlet() {
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

		String id = request.getParameter("id");

		String clazz = request.getParameter("clazz");

		String grade = request.getParameter("grade");

		String major = request.getParameter("major");

		String college = request.getParameter("college");

		String dates = request.getParameter("dates");

		String auto = request.getParameter("auto");

		Clazz cl = new Clazz();

		cl.setId(Integer.parseInt(id));
		cl.setClazzn(clazz);
		cl.setAuto(auto);
		cl.setMajonr(major);
		cl.setDates(dates);
		cl.setAuto(auto);
		cl.setCollegen(college);
		cl.setGraden(grade);
		
		
		//System.out.println(cl.toString());
		
		
		
		try {
			if(ManageFactory.getInstance().doUpClazz(cl)) {
				pw.write("修改成功！");
				pw.flush();
				pw.close();
				return;
			}else {
				pw.write("修改失败！");
				pw.flush();
				pw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			pw.write("内部异常");
			pw.flush();
			pw.close();
		}
		

	}

}
