package jevera.education.booker.domain.dto;

import jevera.education.booker.domain.Room;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

@Data
@Component
public class EventScheduleOnceDto {

    private Room room;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull
    @DateTimeFormat(pattern = "HH-mm")
    private LocalTime timeFrom;
    @NotNull
    @DateTimeFormat(pattern = "HH-mm")
    private LocalTime timeTo;

}