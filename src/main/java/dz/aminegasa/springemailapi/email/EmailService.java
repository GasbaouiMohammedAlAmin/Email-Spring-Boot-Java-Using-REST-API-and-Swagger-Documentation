package dz.aminegasa.springemailapi.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    List<Email> emailList;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        emailList=new ArrayList();// to create new object from email list class
    }
       public void sendSimpleEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getEmailReceiver());
        message.setText(email.getBody());
        message.setSubject(email.getSubject());
        mailSender.send(message);
        System.out.println("Mail Send");
        emailList.add(email);
    }

    public List<Email> getEmailList() {

        return emailList;
    }
    public List <Email> getEmailById(Integer id){
       return emailList.stream().filter(email -> email.getId().equals(id))
               .collect(Collectors.toList());//to return all the emails with a specific id
    }
}

