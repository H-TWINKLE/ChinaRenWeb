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
import com.jwj.entity.UserQues;

/**
 * Servlet implementation class ShareQuesServlet
 */
@WebServlet("/ShareQuesServlet")
public class ShareQuesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareQuesServlet() {
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
		
		User user = (User) request.getSession().getAttribute("admin");
		
		String ques_title = (String)request.getParameter("ques_title");
		String ques_content = (String)request.getParameter("ques_content");
		
		
		System.out.println(ques_content+ques_title);
		
		
		if("".equals(ques_title)||"".equals(ques_content)) {
			pw.write("请填写内容！");
			pw.flush();
			pw.close();
			return;
		}
		
		try {
			if(MessFactory.getInstance().insertOneQues(new UserQues(ques_title,ques_content,user))) {
				pw.write("发布成功！");
				pw.flush();
				pw.close();
				return;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
