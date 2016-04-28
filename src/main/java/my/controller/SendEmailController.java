package my.controller;

import my.entity.EmailsToPersonal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

@RestController
public class SendEmailController {


    @RequestMapping(value = "/sendEmail/", method = RequestMethod.POST)
    public ResponseEntity<Void> sendEmail(@RequestBody EmailsToPersonal emailsBLIA) {

        System.out.println("Sending emails to " + emailsBLIA.getEmails().length + " users");

        if (emailsBLIA.getEmails().length == 0) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }

        String subject = emailsBLIA.getSubject();
        String text = emailsBLIA.getText() + " <br>Time: <a href=\"http://htmlbook.ru/html/a/href\">"
                + new SimpleDateFormat("yyyy_MMM_dd_HH:mm").format(Calendar.getInstance().getTime()) + "</a>";

        sendLettersToEmails(emailsBLIA.getEmails(), subject, text, emailsBLIA.getAdmin_email(), emailsBLIA.getAdmin_password());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Send letters to emails
     *
     * @param toEmails          Emails where letters will be send
     * @param subject           Subject of the letter
     * @param text              Text of the letter
     * @param fromEmail         Email from which letters will be send
     * @param fromEmailPassword Email Password from which letters will be send
     */
    private void sendLettersToEmails(String[] toEmails, String subject, String text,
                                     String fromEmail, String fromEmailPassword) {

        final String username = fromEmail;
        final String password = fromEmailPassword;

        /**
         * Properties for gmail: https://support.google.com/mail/answer/13287?hl=en
         */
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);

            /**
             * From which address(Email) letters will be sending
             * Must be turn on here: https://www.google.com/settings/security/lesssecureapps
             */
            message.setFrom(new InternetAddress(username));

            InternetAddress[] addressTo = new InternetAddress[toEmails.length];
            for (int i = 0; i < toEmails.length; i++)
            {
                addressTo[i] = new InternetAddress(toEmails[i]);
            }

            message.setRecipients(Message.RecipientType.BCC, addressTo);

            message.setSubject(subject);

            message.setContent(text, "text/html");

            Transport.send(message);

            System.out.println("Letter with subject " + subject + " was sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
