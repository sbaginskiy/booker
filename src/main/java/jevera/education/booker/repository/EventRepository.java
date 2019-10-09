package jevera.education.booker.repository;

import jevera.education.booker.domain.Customer;
import jevera.education.booker.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByEventOwner(Customer owner);
    @Query("select event from EventDto event where :customer in event.invited")
    List<Event> findByInvitedCustomer(Customer customer);
}
