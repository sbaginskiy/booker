package jevera.education.booker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class BookerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BookerApplication.class, args);
	}

}
