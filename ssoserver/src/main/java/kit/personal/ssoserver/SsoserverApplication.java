package kit.personal.ssoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SsoserverApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SsoserverApplication.class, args);
	}
}
