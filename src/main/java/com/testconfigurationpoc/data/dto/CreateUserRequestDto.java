package com.testconfigurationpoc.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {
    private String username;
    private String password;
    private String birthDate;
}
