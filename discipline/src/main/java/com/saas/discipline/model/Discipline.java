package com.saas.discipline.model;

import com.saas.discipline.enums.DecisionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "disciplines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discipline extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID employeeId;
    private UUID offenderId;
    private Integer repetition;
    private LocalDateTime offenseDate;
    private String description;
    private String remark;
    private DecisionStatus status;

    @ManyToOne
    @JoinColumn(name = "offense_id")
    private Offense offense;
    @OneToOne(mappedBy = "discipline")
    private Appeal appeal;
}

