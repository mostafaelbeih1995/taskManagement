package banquemisr.challenge05.service.email;

import banquemisr.challenge05.model.payload.MailRequest;
import banquemisr.challenge05.model.payload.MailResponse;

public interface EmailService {

    MailResponse sendEmail(MailRequest request);
}
