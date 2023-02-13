package dz.aminegasa.springemailapi.email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Email {
    private Integer id;
    @javax.validation.constraints.Email // annotation to validate the email
    private String emailReceiver;
    @NotNull
    @Size(min = 3, max = 50)// to  validate the subject min length 3  and max 50
    private String subject;
    @Size(min = 5)
    private String body;
    private LocalDateTime dateTime;//current date time email sending

    public Email(String emailReceiver, String subject, String body) {
        this.emailReceiver = emailReceiver;
        this.subject = subject;
        this.body = body;
        this.dateTime=LocalDateTime.now();
        this.id= EmailId.autoIncrements();

    }

    public String getEmailReceiver() {
        return emailReceiver;
    }

    public String getSubject() {
        return subject;
    }

    public Integer getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
