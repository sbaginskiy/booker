package jevera.education.booker.web;

import jevera.education.booker.domain.Customer;
import jevera.education.booker.domain.Event;
import jevera.education.booker.domain.EventSchedule;
import jevera.education.booker.domain.dto.EventDto;
import jevera.education.booker.domain.dto.EventScheduleDto;
import jevera.education.booker.service.EventScheduleService;
import jevera.education.booker.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventScheduleService eventScheduleService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HttpSession httpSession;

    @PostMapping("/events")
    public ResponseEntity<Event> save(@RequestBody EventDto eventDto,
                                      @RequestBody EventScheduleDto eventScheduleDto) {

        Event event = modelMapper.map(eventDto, Event.class);
        EventSchedule eventSchedule = modelMapper.map(eventScheduleDto, EventSchedule.class);

        eventScheduleService.save(eventSchedule);
        return new ResponseEntity<>(eventService.save(event, getCustomer()), HttpStatus.OK);
    }

    @PutMapping("/events-cancel-path")
    public ResponseEntity<Void> cancelPeriod(@PathVariable("id") Event event,
                                              @RequestBody EventScheduleDto eventScheduleDto) {

        EventSchedule eventSchedule = modelMapper.map(eventScheduleDto, EventSchedule.class);
        eventScheduleService.cancelPeriod(event, eventSchedule);
        return ResponseEntity.ok().build();
    }


    private Customer getCustomer() {
        return (Customer) httpSession.getAttribute("customer");
    }
}
