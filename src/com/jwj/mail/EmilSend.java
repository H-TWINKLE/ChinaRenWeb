package com.jwj.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * �ʼ�������
 */
public class EmilSend {
    /**
     * �����ʼ�
     * @param to ��˭��
     * @param text ��������
     */
    public  void send_mail(String to,String text,String theme) throws MessagingException {
        //�������Ӷ��� ���ӵ��ʼ�������
        Properties properties = new Properties();
        //���÷����ʼ��Ļ�������
        //�����ʼ�������
        properties.put("mail.smtp.host", "smtp.126.com");
        //���Ͷ˿�
        properties.put("mail.smtp.port", "25");
        // ��Ҫ������֤
        properties.put("mail.smtp.auth", "true");
        //���÷����ʼ����˺ź�����
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //���������ֱ��Ƿ����ʼ����˻�������
                return new PasswordAuthentication("zhanghjqqqq@126.com","QQ1349515922");
            }
        });

        //�����ʼ�����
        Message message = new MimeMessage(session);
        //���÷�����
        message.setFrom(new InternetAddress("zhanghjqqqq@126.com"));
        //�����ռ���
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        //��������
        message.setSubject(theme);        
        //�����ʼ�����  �ڶ����������ʼ����͵�����
        message.setContent(text,"text/html; charset=UTF-8");
        //����һ���ʼ�
        Transport.send(message);
    }
}