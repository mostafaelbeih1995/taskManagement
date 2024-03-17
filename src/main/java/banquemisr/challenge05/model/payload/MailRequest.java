package banquemisr.challenge05.model.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MailRequest {

    Map<String, Object> model;

    String template;

    private String name;

    private String to;

    @NotBlank
    private String from;

    private String fromName;

    @NotBlank
    private String subject;

    private MultipartFile file;

    private String fileName;

    private Set<String> bcc;
}
