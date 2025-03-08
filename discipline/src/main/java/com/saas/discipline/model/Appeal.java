package com.saas.discipline.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "appeals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appeal extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String remark;
    @OneToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

}
