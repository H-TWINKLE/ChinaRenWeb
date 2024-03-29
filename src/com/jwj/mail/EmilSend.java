package com.jwj.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件工具类
 */
public class EmilSend {
    /**
     * 发送邮件
     * @param to 给谁发
     * @param text 发送内容
     */
    public  void send_mail(String to,String text,String theme) throws MessagingException {
        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();
        //设置发送邮件的基本参数
        //发送邮件服务器
        properties.put("mail.smtp.host", "smtp.126.com");
        //发送端口
        properties.put("mail.smtp.port", "25");
        // 需要请求认证
        properties.put("mail.smtp.auth", "true");
        //设置发送邮件的账号和密码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码
                return new PasswordAuthentication("zhanghjqqqq@126.com","QQ1349515922");
            }
        });

        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("zhanghjqqqq@126.com"));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        //设置主题
        message.setSubject(theme);        
        //设置邮件正文  第二个参数是邮件发送的类型
        message.setContent(text,"text/html; charset=UTF-8");
        //发送一封邮件
        Transport.send(message);
    }
}