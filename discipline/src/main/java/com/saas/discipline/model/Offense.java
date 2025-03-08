package com.saas.discipline.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "offenses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offense extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String code;
    private Double phaseOutTime;
    private Double weight;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "offense_penalty",
            joinColumns = @JoinColumn(name = "offense_id"),
            inverseJoinColumns = @JoinColumn(name = "penalty_id")
    )
    private List<Penalty> penalties;

}
