package jevera.education.booker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Setter @Getter
@AllArgsConstructor
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
    @ManyToOne @JoinColumn(name = "event_id")
    private Event event;

    public Customer(String login) {
        this.login = login;
    }

}
