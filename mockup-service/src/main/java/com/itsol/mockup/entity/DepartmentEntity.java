package com.itsol.mockup.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTO_INCRE_SEQ_DEPARTMENT")
    @SequenceGenerator(name = "AUTO_INCRE_SEQ_DEPARTMENT", sequenceName = "AUTO_INCRE_SEQ_DEPARTMENT", allocationSize = 1)
    Long id;

    @Column(name = "manager_id", nullable = false)
    Long managerId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "location",nullable = false)
    String location;
}
