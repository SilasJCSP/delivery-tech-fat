package com.deliverutech.delivery_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import org.springframework.boot.jackson.autoconfigure.JacksonProperties.Json;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;

    @Column(unique = true)
    private String email;

    @Builder.Default
    private Boolean ativo = true;

    @Builder.Default
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @JsonIgnore
    public void inativar() {
        this.ativo = false;
    }

    public Cliente(Long id, String nome, String email, String telefone, String endereco, 
        LocalDateTime dataCriacao, Boolean ativo, Object unused) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
    }
}
