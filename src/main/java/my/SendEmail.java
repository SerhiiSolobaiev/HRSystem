package my;

public class SendEmail {

    private String from;
    private String[] to;
    private String subject;
    private String text;

    public SendEmail(String from, String[] to, String subject, String text) {
        this.from= from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getFrom() {
        return from;
    }

    public String[] getTo() {
        return to;
    }
}
