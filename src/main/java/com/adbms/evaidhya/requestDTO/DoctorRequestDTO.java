package com.adbms.evaidhya.requestDTO;


import com.adbms.evaidhya.entity.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDTO {

    private String firstName;

    private String lastName;

    private String phoneNo;

    private String email;

    private String specialization;

    private Integer yearsOfExperience;

    @OneToOne(cascade= CascadeType.ALL)
    private Address address;
}
