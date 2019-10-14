package jevera.education.booker.repository;

import jevera.education.booker.domain.Event;
import jevera.education.booker.domain.EventSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventScheduleRepository extends JpaRepository<EventSchedule, Long> {

    @Query("select es.event from EventSchedule es where es.room = :#{#esNew.room} and " +
            "es.day = :#{#esNew.day} and (((:#{#esNew.dateTo} is null and (es.dateFrom <= :#{#esNew.dateFrom} and " +
            "(es.dateTo >= :#{#esNew.dateFrom} or es.dateTo is null))) or (:#{#esNew.dateTo} is not null and " +
            "((:#{#esNew.dateFrom} >= es.dateFrom and :#{#esNew.dateFrom} <= es.dateTo and :#{#esNew.dateTo} >= es.dateFrom and " +
            ":#{#esNew.dateTo} <= es.dateTo) or (:#{#esNew.dateFrom} <= es.dateFrom and :#{#esNew.dateTo} >= es.dateFrom and " +
            ":#{#esNew.dateTo} <= es.dateTo) or (:#{#esNew.dateFrom} >= es.dateFrom and :#{#esNew.dateFrom} <= es.dateTo and " +
            ":#{#esNew.dateTo} >= es.dateTo) or (:#{#esNew.dateFrom} <= es.dateFrom and :#{#esNew.dateTo} >= es.dateTo)))) and " +
            "((:#{#esNew.timeFrom} >= es.timeFrom and :#{#esNew.timeFrom} <= es.timeTo and :#{#esNew.timeTo} >= es.timeFrom and " +
            ":#{#esNew.timeTo} <= es.timeTo) or (:#{#esNew.timeFrom} <= es.timeFrom and :#{#esNew.timeTo} >= es.timeFrom and " +
            ":#{#esNew.timeTo} <= es.timeTo) or (:#{#esNew.timeFrom} >= es.timeFrom and :#{#esNew.timeFrom} <= es.timeTo and " +
            ":#{#esNew.timeTo} >= es.timeTo) or (:#{#esNew.timeFrom} <= es.timeFrom and :#{#esNew.timeTo} >= es.timeTo)))")
    List<EventSchedule> compareTime(@Param("esNew") EventSchedule eventSchedule);


    @Query("select es from EventSchedule es where es.dateFrom > NOW() and es.event = :event")
    List<EventSchedule> findByEvent(@Param("event") Event event);
}
