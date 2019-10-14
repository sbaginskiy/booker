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
public class EventScheduleDto {

    private Room room;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH-mm")
    private
    LocalTime timeFrom;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH-mm")
    private LocalTime timeTo;

}
