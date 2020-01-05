package com.itsol.mockup.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_TEAM")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_TEAM", sequenceName = "AUTO_INCRE_SEQ_TEAM", allocationSize = 1)
    Long id;

    @Column(name = "leader_id", nullable = false)
    Long leaderId;
}
