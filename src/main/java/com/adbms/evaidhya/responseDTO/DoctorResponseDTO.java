package com.adbms.evaidhya.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {

    private String username;

    private String password;

    private Long userId;

    private Long doctorId;
}
