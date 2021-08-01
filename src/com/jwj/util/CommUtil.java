package com.jwj.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jwj.init.InitString;

public class CommUtil {

	private static CommUtil instance = new CommUtil();

	private CommUtil() {
	}

	public static CommUtil getInstance() {
		return instance;
	};

	public boolean Regex(String input, String regex) {
		Matcher m = Pattern.compile(regex).matcher(input);
		return m.find();
	}

	public String productCode(int length) {
		String base = InitString.RadomString;
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public StringBuffer toEmilText(String admin, String url) {
		StringBuffer content = new StringBuffer();
		content.append("<div><div style='margin-left:4%;'>");
		content.append("<p style='color:red;'>");
		content.append("用户  " + admin + "，您好：</p>");
		content.append("<p style='text-indent: 2em;'>您好！您正在进行邮箱验证，在浏览器地址栏里输入并访问以下激活链接即可进行账户验证：</p>");
		content.append("<a href=\"" + url + "\"><p style='text-indent: 2em;display: block;word-break: break-all;'>"
				+ url + "</p></a>");
		content.append("</div>");
		content.append("<ul style='color: rgb(169, 169, 189);font-size: 18px;'>");
		content.append("<li><h5>请您妥善保管，此邮件无需回复。</h5></li>");
		content.append("</ul>");
		content.append("</div>");
		return content;

	}

	public String isServicePath(String servlet) {
		String path = System.getProperty("java.home");
		boolean flag = getInstance().Regex(path, "ProgramFile");
		return flag ? InitString.emailLocalUrl + servlet + "?action=post&code="
				: InitString.emailTomcatUrl + servlet + "?action=post&code=";
	}

	public String stampToDate(String s) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(new Long(s)));
		} catch (Exception e) {
			return "";
		}

	}

}
