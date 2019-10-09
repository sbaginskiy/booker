package jevera.education.booker.repository;

import jevera.education.booker.domain.EventSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventScheduleRepository extends JpaRepository<EventSchedule, Long> {

    @Query("select es.event from EventSchedule es where es.room = esNew.room and " +
            "es.day = esNew.day")
    List<EventSchedule> compareTime(@Param("esNew") EventSchedule eventSchedule);
}
