package com.itsol.mockup.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_PROJECT")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_PROJECT", sequenceName = "AUTO_INCRE_SEQ_PROJECT", allocationSize = 1)
    Long id;

    @Column(name = "name",nullable = false,unique = true)
    String name;

    @Column(name = "status_id", nullable = false)
    Long statusId;


}
