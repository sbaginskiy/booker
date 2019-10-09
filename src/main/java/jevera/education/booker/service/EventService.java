package jevera.education.booker.service;

import jevera.education.booker.domain.Customer;
import jevera.education.booker.domain.Event;
import jevera.education.booker.exceptions.BussinesException;
import jevera.education.booker.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    private static final String TIME_ALREADY_TAKEN = "Selected time already taken";

    public Event save(Event event, Customer customer) {
//        assertAvailableTime(event);
        event.setEventOwner(customer);
        return eventRepository.save(event);
    }

//    private void assertAvailableTime(EventDto event) {
//        List<EventDto> result = eventRepository.compareEvent(event);
//        if (result.isEmpty())
//            return;
//        throw new BussinesException("Time already taken by such events: " + result);
//    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new BussinesException("No EventDto with id " + id + " found."));
    }

    public List<Event> findByOwner(Customer owner) {
        return eventRepository.findByEventOwner(owner);
    }

    public List<Event> findByInvitedCustomer(Customer customer) {
        return eventRepository.findByInvitedCustomer(customer);
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}

