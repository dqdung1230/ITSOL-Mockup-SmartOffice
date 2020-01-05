package com.itsol.mockup.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class EmpIssuePK implements Serializable {


    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "issue_id")
    Long issueId;
}
