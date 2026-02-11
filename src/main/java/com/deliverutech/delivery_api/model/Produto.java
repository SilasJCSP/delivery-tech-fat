package com.deliverutech.delivery_api.model;

//import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String categoria;

    private String descricao;

    private Double preco;

    @Builder.Default
    private boolean disponivel = true;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    public boolean getAtivo() {
        return this.disponivel != null ? this.disponivel : false;
    }

    public void setAtivo(boolean ativo) {
        this.disponivel = ativo;
    }

}
