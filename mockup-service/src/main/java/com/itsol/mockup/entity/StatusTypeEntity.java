package com.itsol.mockup.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "status_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StatusTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_STATUS_TYPE")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_STATUS_TYPE", sequenceName = "AUTO_INCRE_SEQ_STATUS_TYPE", allocationSize = 1)
    Long id;

    @Column(name = "name")
    String name;
}
