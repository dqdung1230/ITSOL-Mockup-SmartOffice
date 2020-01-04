package com.itsol.mockup.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "employee_issue")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class EmployeeIssueEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",nullable = false)
    EmployeeEntity employeeEntity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    IssueEntity issueEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    StatusEntity statusEntity;

    @Column(name = "spent_time",nullable = false)
    Float spentTime;

    @Column(name = "note")
    String note;


}
