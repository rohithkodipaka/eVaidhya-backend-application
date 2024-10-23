package com.adbms.evaidhya.responseDTO;

import com.adbms.evaidhya.entity.Address;
import com.adbms.evaidhya.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDTO {

    private Long patientId;

    private String firstName;

    private String lastName;

    private String phoneNo;

    private String email;

    private Address address;

    private String gender;
}
