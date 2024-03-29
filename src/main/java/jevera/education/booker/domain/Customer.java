package jevera.education.booker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "login")
@ToString
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "event_id", insertable = false, updatable = false)
    @Column(name = "id")
    private Long id;
    private String login;
    @JsonIgnore
    private String passwordHash;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "customer_event",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    Set<Event> events = new HashSet<>();

    public Customer(String login) {
        this.login = login;
    }

}
