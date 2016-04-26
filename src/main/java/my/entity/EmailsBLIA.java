package my.entity;

import java.io.Serializable;
import java.util.Arrays;

public class EmailsBLIA {
    String subject;
    String text;
    String[] emails;
    String admin_email;
    String admin_password;

    public EmailsBLIA() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getSubject() {
        return subject;
    }

    public String[] getEmails() {
        return emails;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    @Override
    public String toString() {
        return "EmailsBLIA{" +
                "subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", emails=" + Arrays.toString(emails) +
                ", admin_email='" + admin_email + '\'' +
                ", admin_password='" + admin_password + '\'' +
                '}';
    }
}
