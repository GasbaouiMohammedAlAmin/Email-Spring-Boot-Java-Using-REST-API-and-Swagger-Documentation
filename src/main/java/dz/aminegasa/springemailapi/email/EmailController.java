package dz.aminegasa.springemailapi.email;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("API allows you to send a simple email using your own one , " +
        "you can send many mails at once by determine the nbrEmail" +
        "parameter.")
//this class for REST API
@RestController
@RequestMapping(path = "api/v1/email")
//this class respond when I visit this path in the url
public class EmailController {
    @Autowired
    private final EmailService emailService;

    public EmailController(EmailService emailService) {

        this.emailService = emailService;
    }

    @ApiOperation(value = "function returns a string description api")
    //this annotation to show this message beside the function projectDescription
    @GetMapping
    public String projectDescription() {
        return "this is a simple api for email sender" +
                " created by spring boot ";
    }

    @ApiOperation(value = "this function allows you to see all sent email in json format")
    @GetMapping(path = "allEmails")
    public List<Email> AllEmails() {
        return emailService.getEmailList();
    }

    @ApiOperation(value = "this function allows you to get a specific email id ")
    @GetMapping(path = "{emailId}")

    public List<Email> getEmailById(@PathVariable("emailId") Integer emailId) {
        //this function accept emailId as a parameter in the url (get request)
           return emailService.getEmailById(emailId);
    }

    @PostMapping
    @ApiOperation(value = "this function allows you to send an email" +
            ", required body parameters are :emailReceiver,subject and body , " +
            "you can send a specific" +
            "number of email at once by determining  nbrEmail parameter")
    public void sendEmail( @Valid @RequestBody Email email, @RequestParam(required = false)
            //valid annotation to validate the email object created
    Integer nbrEmail) {
        if (nbrEmail == null)
            nbrEmail = 1;//by default, I keep it 1
        for (int i = 0; i < nbrEmail; i++) {
            emailService.sendSimpleEmail(email);
            //i loop till i reach nbrEmail input parameter
        }


    }
}
