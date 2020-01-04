package com.itsol.mockup.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "team_project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TeamProjectEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "team_id",nullable = false)
    TeamEntity teamEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    ProjectEntity projectEntity;

    @Column(name = "start_date",nullable = false)
    Date startDate;

    @Column(name = "handover_date", nullable = false)
    Date handoverDate;
}
