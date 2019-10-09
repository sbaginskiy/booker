package jevera.education.booker.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter @Setter
@ToString @AllArgsConstructor
@EqualsAndHashCode(of = "type")
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "event_id", insertable = false, updatable = false)
 @Column(name = "id")
 private Long id;
 @Column(name = "type")
 private String type;

}
