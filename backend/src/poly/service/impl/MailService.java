package poly.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service("MailService")
public class MailService implements IMailService {

    private Logger log = Logger.getLogger(this.getClass());

    final String host = "smtp.office365.com";
    final String user = "1920110012@student.kopo.ac.kr";
    final String password = "407412ad!@";

    @Override
    public int sendEmail(MailDTO pDTO) {
        log.info(this.getClass().getName() + " : sendEmail 호출");

        int res = 1;

        if(pDTO == null) {
            pDTO = new MailDTO();
        }

        String toMail = CmmUtil.nvl(pDTO.getToMail());

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
        props.put("mail.smtp.host", "smtp.office365.com");      // smtp 서버 주소
        props.put("mail.smtp.auth","true");                 // gmail은 무조건 true 고정
        props.put("mail.smtp.port", "587");                 // gmail 포트

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

            message.setSubject(CmmUtil.nvl(pDTO.getTitle()));

            message.setText(CmmUtil.nvl(pDTO.getContents()));

            Transport.send(message);
        } catch (MessagingException e) {
            res = 0;
            log.info("[ERROR] " + this.getClass().getName() + ".doSendMail : " + e);
        } catch (Exception e) {
            res = 0;
            log.info("[ERROR] " + this.getClass().getName() + ".doSendMail : " + e);
        }

        log.info(this.getClass().getName() + ".doSendMail end!!");
        return res;
    }
}
