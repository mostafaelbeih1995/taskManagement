package banquemisr.challenge05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@EnableScheduling
public class Challenge05Application {

	public static void main(String[] args) {
		SpringApplication.run(Challenge05Application.class, args);
	}

}
