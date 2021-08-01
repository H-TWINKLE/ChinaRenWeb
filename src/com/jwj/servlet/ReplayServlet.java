package com.jwj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jwj.dao.factory.MessFactory;
import com.jwj.entity.Replayuq;
import com.jwj.entity.User;

/**
 * Servlet implementation class ReplayServlet
 */
@WebServlet("/ReplayServlet")
public class ReplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplayServlet() {
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
	
		int id = Integer.parseInt(request.getParameter("id"));

		int flag = Integer.parseInt(request.getParameter("flag"));

		List<Replayuq> list;

		if (flag == 1) {
			try {
				list = MessFactory.getInstance().getReplayById(id);

				/*for (Replayuq re : list) {
					System.out.println("name:" + re.getU_id().getName() + "content:" + re.getContent());
				}
*/
				pw.write(JSONObject.toJSONString(list));
				pw.flush();
				pw.close();
				return;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (flag == 2) {
			String text = (String) request.getParameter("text");
			if ("".equals(text)) {
				pw.write("·¢ËÍÊ§°Ü,ÇëÌîÐ´ÄÚÈÝ£¡");
				pw.flush();
				pw.close();
				return;
			}

			try {
				User user = (User) request.getSession().getAttribute("admin");
				
				if (MessFactory.getInstance().addReplayById(new Replayuq(id, text, new User(user.getId())))) {
					pw.write("·¢ËÍ³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				} else {
					pw.write("·¢ËÍÊ§°Ü£¡");
					pw.flush();
					pw.close();
					return;
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}else if (flag == 3) {
			String text = (String) request.getParameter("text");
			if ("".equals(text)) {
				pw.write("·¢ËÍÊ§°Ü,ÇëÌîÐ´ÄÚÈÝ£¡");
				pw.flush();
				pw.close();
				return;
			}

			try {
				if (MessFactory.getInstance().addTip(id, text, 0)) {
					pw.write("·¢ËÍ³É¹¦£¡");
					pw.flush();
					pw.close();
					return;
				} else {
					pw.write("·¢ËÍÊ§°Ü£¡");
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
