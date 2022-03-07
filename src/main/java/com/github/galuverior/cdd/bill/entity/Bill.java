package com.github.galuverior.cdd.bill.entity;

import com.github.galuverior.cdd.client.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Bill")
@Table(name = "conta")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_competencia")
    private LocalDate competencia;

    @Column(name = "data_vencimento")
    private LocalDate vencimento;

    @Column(name = "valor")
    private Long valor;

    @OneToOne
    @JoinColumn(name = "id_categoria")
    private Category categoria;

    @Column(name = "situacao")
    private String situacao;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Client cliente;
}
