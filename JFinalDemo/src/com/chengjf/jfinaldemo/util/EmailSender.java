package com.chengjf.jfinaldemo.util;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 鍙戦�佹櫘閫氶偖浠讹紝鎺ュ彈鏅�氶偖浠� 鍙戦�佸甫鏈夐檮浠剁殑閭欢锛屾帴鏀跺甫鏈夐檮浠剁殑閭欢 鍙戦�乭tml褰㈠紡鐨勯偖浠讹紝鎺ュ彈html褰㈠紡鐨勯偖浠� 鍙戦�佸甫鏈夊浘鐗囩殑閭欢绛夊仛浜嗕竴涓�荤粨銆�
 */
public class EmailSender {

    public static final String EMAIL_BODY_HEADER = "";
    // 閭鏈嶅姟鍣�
    private String host = "smtp.exmail.qq.com";
    private String MAIL_SUBJECT = "娴嬭瘯閭欢";
    private String PERSONAL_NAME = "鏈嬩篃";
    private String username;
    private String password;
    private String mail_from;

    static class EmailSenderHolder {
        static Prop prop = PropKit.getProp("config.properties");
        static EmailSender instance = new EmailSender(prop.get("email.username"), prop.get("email.password"));
    }

    public static EmailSender getInstance() {
        return EmailSenderHolder.instance;
    }

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;
        this.mail_from = username;
    }

    /**
     * 姝ゆ浠ｇ爜鐢ㄦ潵鍙戦�佹櫘閫氱數瀛愰偖浠�
     */
    public void send(String subject, String[] mailTo, String mailBody) throws Exception {
        try {
            Properties props = new Properties(); // 鑾峰彇绯荤粺鐜
            Authenticator auth = new Email_Autherticator(); // 杩涜閭欢鏈嶅姟鍣ㄧ敤鎴疯璇�
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props, auth);
            // 璁剧疆session,鍜岄偖浠舵湇鍔″櫒杩涜閫氳銆�
            MimeMessage message = new MimeMessage(session);
            // message.setContent("foobar, "application/x-foobar"); // 璁剧疆閭欢鏍煎紡
            message.setSubject(subject == null?MAIL_SUBJECT:subject); // 璁剧疆閭欢涓婚
            message.setText(mailBody); // 璁剧疆閭欢姝ｆ枃
//			message.setHeader(mail_head_name, mail_head_value); // 璁剧疆閭欢鏍囬
            message.setSentDate(new Date()); // 璁剧疆閭欢鍙戦�佹棩鏈�
            Address address = new InternetAddress(mail_from, PERSONAL_NAME);
            message.setFrom(address); // 璁剧疆閭欢鍙戦�佽�呯殑鍦板潃
            Address toAddress = null;
            for (int i = 0; i < mailTo.length; i++) {
                toAddress = new InternetAddress(mailTo[i]); // 璁剧疆閭欢鎺ユ敹鏂圭殑鍦板潃
                message.addRecipient(Message.RecipientType.TO, toAddress);
            }
            toAddress = null;
            Transport.send(message); // 鍙戦�侀偖浠�
            System.out.println("send ok!");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 鐢ㄦ潵杩涜鏈嶅姟鍣ㄥ鐢ㄦ埛鐨勮璇�
     */
    public class Email_Autherticator extends Authenticator {
        public Email_Autherticator() {
            super();
        }

        public Email_Autherticator(String user, String pwd) {
            super();
            username = user;
            password = pwd;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    public static void sendMail(String title, String[] mailTo, String content) {
        String mailBody = EMAIL_BODY_HEADER + content;
        try {
            EmailSender.getInstance().send(title, mailTo, mailBody);
        } catch (Exception e) {
            System.out.println("email send error:" + mailBody);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendMail(null, new String[]{""}, "娴嬭瘯閭欢鍐呭");
    }

}
