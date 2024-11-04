package com.adbms.evaidhya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNo;

    private String email;

    private String specialization;

    private Integer yearsOfExperience;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

}
