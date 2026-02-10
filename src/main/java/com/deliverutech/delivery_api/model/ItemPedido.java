package com.deliverutech.delivery_api.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;


    // METODO PARA CALCULAR SUBTOTAL AUTOMATICAMENTE
    @PrePersist
    @PreUpdate
    private void calcularSubtotlal() {
        if (precoUnitario != null && quantidade != null) {
            this.subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
            // Aqui você pode definir o subtotal em um campo específico ou retornar o valor
        }
    }

    // METODO AUXILIAR PARA DEFINIR SUBTOTAL MANUALMENTE SE NECESSARIO
    public void setSubtotal() {
        calcularSubtotlal();
    }

    // METODO AUXILIAR PARA OBTER VALOR TOTAL DO ITEM
    public BigDecimal getValorTotal() {
        return subtotal != null ? subtotal : BigDecimal.ZERO;

}
