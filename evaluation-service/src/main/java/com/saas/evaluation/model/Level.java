package com.saas.evaluation.model;

import com.saas.evaluation.enums.LevelStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Level extends Base {

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private Double minPoint;

    @Column(nullable = false)
    private Double maxValue;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LevelStatus status;

}
