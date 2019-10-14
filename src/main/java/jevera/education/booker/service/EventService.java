package jevera.education.booker.service;

import jevera.education.booker.domain.Customer;
import jevera.education.booker.domain.Event;
import jevera.education.booker.exceptions.BussinesException;
import jevera.education.booker.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private static final String TIME_ALREADY_TAKEN = "Selected time already taken";

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event, Customer customer) {
        event.setEventOwner(customer);
        return eventRepository.save(event);
    }

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

