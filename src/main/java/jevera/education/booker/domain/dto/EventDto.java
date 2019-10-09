package jevera.education.booker.domain.dto;

import jevera.education.booker.domain.Customer;
import jevera.education.booker.domain.Room;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class EventDto {

    private String description;
    private Customer eventOwner;
    private List<Customer> invited;

}


