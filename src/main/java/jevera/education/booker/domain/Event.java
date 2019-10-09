package jevera.education.booker.domain;


import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
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
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
    public  class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "event_id", insertable = false, updatable = false)
    @Column(name = "id")
    private Long id;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "owner_id")
    private Customer eventOwner;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Customer> invited = new ArrayList<>();

    public void id (Long id) {
        this.id = id;
    }

    public boolean checkDate(LocalDate startTime, LocalDate endTime){
        return false;
    }

}



