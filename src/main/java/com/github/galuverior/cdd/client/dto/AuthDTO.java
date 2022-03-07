package com.github.galuverior.cdd.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthDTO {

    private String email;

    private String senha;

    private String token;
}
