package jevera.education.booker;

import static java.util.Arrays.asList;

import jevera.education.booker.domain.Event;
import jevera.education.booker.domain.Customer;
import jevera.education.booker.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class BookerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BookerApplication.class, args);
	}

}
