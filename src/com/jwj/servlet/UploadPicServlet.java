package com.jwj.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jwj.dao.factory.MessFactory;
import com.jwj.dao.factory.UserFactory;
import com.jwj.entity.User;

/**
 * Servlet implementation class UploadPic
 */
@WebServlet("/UploadPicServlet")
public class UploadPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadPicServlet() {
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
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();

		User user = (User) request.getSession().getAttribute("admin");

		String flag = String.valueOf(System.currentTimeMillis());
		
		int f = 0;
		String newFileName = "";
		String what = "";

		if (ServletFileUpload.isMultipartContent(request)) {

			try {
				// 1. ����DiskFileItemFactory�������û�������С����ʱ�ļ�Ŀ¼
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// System.out.println(System.getProperty("java.io.tmpdir"));//Ĭ����ʱ�ļ���

				// 2. ����ServletFileUpload���󣬲������ϴ��ļ��Ĵ�С���ơ�
				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setSizeMax(10 * 1024 * 1024);// ��byteΪ��λ ���ܳ���10M 1024byte = 1kb 1024kb=1M 1024M = 1G
				sfu.setHeaderEncoding("utf-8");

				// 3. ����ServletFileUpload.parseRequest��������request���󣬵õ�һ�������������ϴ����ݵ�List����
				List<FileItem> fileItemList = sfu.parseRequest(request);
				Iterator<FileItem> fileItems = fileItemList.iterator();

				// 4. ����list��ÿ����һ��FileItem���󣬵�����isFormField�����ж��Ƿ����ϴ��ļ�
				while (fileItems.hasNext()) {
					FileItem fileItem = fileItems.next();
					// ��ͨ��Ԫ��
					if (fileItem.isFormField()) {
						String name = (String) fileItem.getFieldName();// name����ֵ
						if (name.equals("f")) {
							f = Integer.parseInt(fileItem.getString("utf-8"));
						}
						if (name.equals("what")) {
							what = fileItem.getString("utf-8");
						}
						// System.out.println("" + f);

					}
					// <input type="file">���ϴ��ļ���Ԫ��
					else {
						// String fileName = fileItem.getName();// �ļ�����
						// System.out.println("ԭ�ļ�����" + fileName);//Koala.jpg

						// String suffix = fileName.substring(fileName.lastIndexOf('.'));
						// System.out.println("��չ����" + suffix);//.jpg

						// ������չ��
						String newsuffix = ".jpg";

						// ���ļ�����Ψһ��
						newFileName = String.valueOf(user.getId()) + "_" + flag + newsuffix;
						// System.out.println("���ļ�����" + newFileName);// image\1478509873038.jpg

						// 5. ����FileItem��write()������д���ļ�
						File file = new File(request.getServletContext().getRealPath("") + "//upImg//" + newFileName);
						// System.out.println(file.getAbsolutePath());
						fileItem.write(file);

						// 6. ����FileItem��delete()������ɾ����ʱ�ļ�
						fileItem.delete();

					}

				}
				try {
					
					System.out.println("f:"+f+"name:"+newFileName);
					
					if (f == 1) {
						if (UserFactory.getInstance().updateImg(newFileName, String.valueOf(user.getId()))) {
							pw.write(newFileName);
							user.setImg(newFileName);
							request.getSession().setAttribute("admin", user);
							pw.close();
							return;
						}
					} else if (f == 2) {
						// System.out.println("" + newFileName + " u" + user.getId() + " ci " +
						// user.getClazz().getId());
						if (UserFactory.getInstance().uploadClazzImg(newFileName, user.getId(),
								user.getClazz().getId())) {
							pw.write(newFileName);														
							pw.close();
							return;
						}
					} else if (f == 3) {
						if (MessFactory.getInstance().uploadUserMess(newFileName, user.getId(), what)) {
							pw.write("���ͳɹ���");							
							pw.close();
							return;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					pw.write("0");
					pw.flush();
					pw.close();
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
				pw.write("0");
				pw.flush();
				pw.close();
				return;
			}

		}

	}

}
