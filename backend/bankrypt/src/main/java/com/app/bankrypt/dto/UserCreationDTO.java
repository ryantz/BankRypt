package com.app.bankrypt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDTO {

    private String email;
    private String firstName;
    private String lastName;
    private Long contactNumber;
    private String username;
    private String password;
}
