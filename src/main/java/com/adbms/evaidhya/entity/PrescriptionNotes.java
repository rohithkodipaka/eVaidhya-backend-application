package com.adbms.evaidhya.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionNotes {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "patient_id", nullable = false)
        private Patient patient;

        @OneToOne
        @JoinColumn(name = "doctor_id", nullable = false)
        private Doctor doctor;

        @OneToOne
        @JoinColumn(name = "appointment_id")
        private Appointment appointment;

        private LocalDateTime prescriptionDate;

        private String medicationName;

        private String dosage;

        private String frequency;

        @Column(length = 1000)
        private String notes;
}
