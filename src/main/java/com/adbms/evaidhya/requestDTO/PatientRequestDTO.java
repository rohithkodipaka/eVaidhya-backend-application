package com.adbms.evaidhya.requestDTO;

import com.adbms.evaidhya.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PatientRequestDTO {

    private String email;

    private String phoneNo;

    private Address address;

    private String gender;
}
