package com.conexus.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    public Schedule(Long id, LocalDateTime startService, LocalDateTime endService, Professional professional) {
        super(id);
        this.startService = startService;
        this.endService = endService;
        this.professional = professional;
    }

    @Column
    LocalDateTime startService;

    @Column
    LocalDateTime endService;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;
}
