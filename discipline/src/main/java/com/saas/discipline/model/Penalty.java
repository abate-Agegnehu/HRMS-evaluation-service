package com.saas.discipline.model;

import com.saas.discipline.enums.PenaltyActionTaker;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "penalties")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Penalty extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String code;
    private String classification;
    private PenaltyActionTaker actionTaker;
    private String description;

    @ManyToMany(mappedBy = "penalties")
    private List<Offense> offenses;

}
