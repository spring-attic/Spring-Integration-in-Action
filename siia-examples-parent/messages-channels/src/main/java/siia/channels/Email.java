package siia.channels;

/**
 * @author Marius Bogoevici
 */
public class Email {

    private String recipient;

    private String content;

    public Email(String recipient, String content) {
        this.recipient = recipient;
        this.content = content;
     }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
