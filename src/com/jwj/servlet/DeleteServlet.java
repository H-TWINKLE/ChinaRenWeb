package com.jwj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwj.dao.factory.ManageFactory;
import com.jwj.dao.factory.MessFactory;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
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

		int flag = Integer.parseInt(request.getParameter("flag"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		System.out.println("flag:"+flag+"id:"+id);

		try {
			if (flag == 1) {
				if (ManageFactory.getInstance().deleteComm(id,"userques","q_id")) {
					pw.write("É¾³ý³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			} else if (flag == 2) {
				if (ManageFactory.getInstance().deleteComm(id,"usermess","m_id")) {
					pw.write("É¾³ý³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			}
			else if (flag == 3) {
				if (ManageFactory.getInstance().deleteComm(id,"clazzmess","id")) {
					pw.write("É¾³ý³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			} 
			else if (flag == 4) {
				if (ManageFactory.getInstance().deleteComm(id,"clazzpic","id")) {
					pw.write("É¾³ý³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			} else if (flag == 5) {
				if (ManageFactory.getInstance().deleteComm(id,"usermess","m_id")) {
					pw.write("É¾³ý³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			}
			else if (flag == 6) {
				if (ManageFactory.getInstance().deleteComm(id,"userques","q_id")) {
					pw.write("É¾³ý³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			}
			else if (flag == 7) {
				if (ManageFactory.getInstance().deleteComm(id,"clazzpic","id")) {
					pw.write("É¾³ý³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			}
			else if (flag == 8) {
				if (ManageFactory.getInstance().deleteComm(id,"tip","id")) {
					pw.write("É¾³ý³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			}else if (flag == 9) {
				if (MessFactory.getInstance().isReadTip(id)) {
					pw.write("²Ù×÷³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			}else if (flag == 12) {
				if (ManageFactory.getInstance().deleteComm(id,"clazzmess","id")) {
					pw.write("É¾³ý³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				}else {
					pw.write("É¾³ýÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			}
		} catch (Exception e) {
			pw.write("ÄÚ²¿Òì³££¡");
			pw.flush();
			pw.close();
			return;
		}

	}

}
