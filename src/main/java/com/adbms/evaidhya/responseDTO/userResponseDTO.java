package com.adbms.evaidhya.responseDTO;


import com.adbms.evaidhya.enumerations.ROLE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class userResponseDTO {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private ROLE userRole;


}
