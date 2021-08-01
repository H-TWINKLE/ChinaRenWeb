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

/**
 * Servlet implementation class CreateClazzServlet
 */
@WebServlet("/CreateClazzServlet")
public class CreateClazzServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClazzServlet() {
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
		
		String graden = request.getParameter("graden");

		String clazz = request.getParameter("clazz");

		String major = request.getParameter("major");

		String college = request.getParameter("college");
		
		String auto = request.getParameter("auto");
			
		Clazz cl = new Clazz(clazz,graden,major,college,auto);
		
		int clazz_id = 0;
		
		try {
			clazz_id = UserFactory.getInstance()
					.findCollege(cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (clazz_id == 0) {
			try {
				if (UserFactory.getInstance()
						.setCollege(cl)) {
					pw.write("创建班级成功！");
					pw.flush();
					pw.close();
					return;
				} else {
					pw.write("内部异常");
					pw.flush();
					pw.close();
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}else {
			pw.write("该班级已创建！");
			pw.flush();
			pw.close();
			return;
		}
		
		
		
	}

}
