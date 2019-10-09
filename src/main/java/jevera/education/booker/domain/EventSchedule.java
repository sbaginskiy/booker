package jevera.education.booker.domain;

import jevera.education.booker.domain.enums.ActionType;
import jevera.education.booker.domain.enums.Days;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "event_schedule")
public class EventSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "event_id", insertable = false, updatable = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;
    @Column(name = "time_from")
    private LocalTime timeFrom;
    @Column(name = "time_to")
    private LocalTime timeTo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Event room;
    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    @Column(name = "day")
    @Enumerated(EnumType.STRING)
    private Days day;

}

