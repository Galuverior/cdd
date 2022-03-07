package com.github.galuverior.cdd.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClientDTO {

    private Long id;

    private String nome;

    private String email;

    private String senha;
}
