package jevera.education.booker.service;

import jevera.education.booker.domain.EventSchedule;
import jevera.education.booker.exceptions.BussinesException;
import jevera.education.booker.repository.EventScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventScheduleService {

    @Autowired
    private EventScheduleRepository eventScheduleRepository;

    public EventSchedule save(EventSchedule eventSchedule) {
        assertTime(eventSchedule);
        return eventScheduleRepository.save(eventSchedule);
    }

    private void assertTime(EventSchedule eventSchedule) {
        List<EventSchedule> result = eventScheduleRepository.compareTime(eventSchedule);
        if (result.isEmpty())
            return;
        throw new BussinesException("Time already taken by such events: " + result.stream().map(EventSchedule::getEvent));
    }
}
