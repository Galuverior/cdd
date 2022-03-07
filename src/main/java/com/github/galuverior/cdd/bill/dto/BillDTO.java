package com.github.galuverior.cdd.bill.dto;

import com.github.galuverior.cdd.bill.entity.Category;
import com.github.galuverior.cdd.client.entity.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class BillDTO {

    private Long id;

    private String descricao;

    private LocalDate competencia;

    private LocalDate vencimento;

    private Long valor;

    private Category categoria;

    private String situacao;

    private Client cliente;

    private Long clienteId;

    private Long categoriaId;

}
