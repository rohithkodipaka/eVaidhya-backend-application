package com.adbms.evaidhya.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id",nullable = false)
    @JsonBackReference
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private DayOfWeek  dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

}
