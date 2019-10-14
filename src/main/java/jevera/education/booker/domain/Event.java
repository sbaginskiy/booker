package jevera.education.booker.domain;


import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "event")
    public  class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "event_id", insertable = false, updatable = false)
    @Column(name = "id")
    private Long id;
    private String description;
    @ManyToOne(cascade = {PERSIST, MERGE}, fetch = FetchType.LAZY) @JoinColumn(name = "owner_id")
    private Customer eventOwner;
    @ManyToMany(mappedBy = "events")
    private List<Customer> invited = new ArrayList<>();

}



