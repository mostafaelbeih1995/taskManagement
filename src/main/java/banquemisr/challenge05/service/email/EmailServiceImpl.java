package banquemisr.challenge05.service.email;

import banquemisr.challenge05.model.payload.MailRequest;
import banquemisr.challenge05.model.payload.MailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public MailResponse sendEmail(MailRequest request) {
        MailResponse response = new MailResponse();
        SimpleMailMessage message = new SimpleMailMessage();
        try{
            message.setFrom("mostafa.elbeihjr@gmail.com");
            message.setTo("mostafa.elbeihjr@gmail.com");
            message.setSubject(request.getSubject());
            message.setText(request.getTemplate());
            javaMailSender.send(message);
            response.setMessage("Mail sent successfully");
            response.setStatus(Boolean.TRUE);
        } catch (Exception e){
            response.setMessage("Mail Send Failure");
            response.setStatus(Boolean.FALSE);

        }
        return response;
    }
}
