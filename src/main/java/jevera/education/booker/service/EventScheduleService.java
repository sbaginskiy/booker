package jevera.education.booker.service;

import jevera.education.booker.domain.Event;
import jevera.education.booker.domain.EventSchedule;
import jevera.education.booker.domain.enums.Days;
import jevera.education.booker.exceptions.BussinesException;
import jevera.education.booker.repository.EventScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class EventScheduleService {


    private final EventScheduleRepository eventScheduleRepository;
    private final ModelMapper modelMapper;

    public EventScheduleService(EventScheduleRepository eventScheduleRepository, ModelMapper modelMapper) {
        this.eventScheduleRepository = eventScheduleRepository;
        this.modelMapper = modelMapper;
    }

    public EventSchedule save(EventSchedule eventSchedule) {
        setDay(eventSchedule);
        assertTime(eventSchedule);
        return eventScheduleRepository.save(eventSchedule);
    }

    public void cancelPeriod(Event event, EventSchedule eventSchedule) {
        findByEvent(event).forEach(it -> modify(it, eventSchedule));
    }

    private void modify(EventSchedule esOld, EventSchedule esNew) {
        if (esNew.getDateFrom().isAfter(esOld.getDateFrom()) && esNew.getDateTo().isBefore(esOld.getDateTo())) {
            EventSchedule secondPath = new EventSchedule();
            secondPath.setDateFrom(esNew.getDateTo());
            secondPath.setDateTo(esOld.getDateTo());
            secondPath.setDay(esOld.getDay());
            secondPath.setTimeFrom(esOld.getTimeFrom());
            secondPath.setTimeTo(esOld.getTimeTo());
            secondPath.setRoom(esOld.getRoom());
            secondPath.setEvent(esOld.getEvent());
            esOld.setDateTo(esNew.getDateFrom());
            eventScheduleRepository.save(secondPath);
            eventScheduleRepository.save(esOld);
        } else if (esNew.getDateFrom().isAfter(esOld.getDateFrom()) && esNew.getDateFrom().isBefore(esOld.getDateTo()) &&
                esNew.getDateTo().isAfter(esOld.getDateTo())) {
            esOld.setDateTo(esOld.getDateFrom());
            eventScheduleRepository.save(esOld);
        } else if (esNew.getDateFrom().isBefore(esOld.getDateFrom()) && esNew.getDateTo().isAfter(esOld.getDateFrom()) &&
                esNew.getDateTo().isBefore(esOld.getDateTo())) {
            esOld.setDateFrom(esNew.getDateTo());
            eventScheduleRepository.save(esOld);
        } else if (esNew.getDateFrom().isBefore(esOld.getDateFrom()) && esNew.getDateTo().isAfter(esOld.getDateTo())) {
            eventScheduleRepository.delete(esOld);
        }
    }

    private List<EventSchedule> findByEvent(Event event) {
        return eventScheduleRepository.findByEvent(event);
    }

    private void setDay(EventSchedule eventSchedule) {
        if (eventSchedule.getDateTo() == null) {
            eventSchedule.setDay(DayOfWeekToDays(eventSchedule.getDateFrom().getDayOfWeek()));
        }
    }

    private Days DayOfWeekToDays(DayOfWeek dayOfWeek) {
        return Days.values()[dayOfWeek.ordinal()];
    }

    private void assertTime(EventSchedule eventSchedule) {
        List<EventSchedule> result = eventScheduleRepository.compareTime(eventSchedule);
        if (result.isEmpty())
            return;
        throw new BussinesException("Time already taken by such events: " + result.stream().map(EventSchedule::getEvent));
    }
}
