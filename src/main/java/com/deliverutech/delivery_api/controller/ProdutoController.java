package com.deliverutech.delivery_api.controller;

import com.deliverutech.delivery_api.dto.request.ProdutoRequest;
import com.deliverutech.delivery_api.dto.response.ProdutoResponse;
import com.deliverutech.delivery_api.model.Produto;
import com.deliverutech.delivery_api.model.Restaurante;
import com.deliverutech.delivery_api.service.ProdutoService;
import com.deliverutech.delivery_api.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final RestauranteService restauranteService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@Valid @RequestBody ProdutoRequest request) {
        Restaurante restaurante = restauranteService.buscarPorId(request.getRestauranteId())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        Produto produto = Produto.builder()
                .nome(request.getNome())
                .categoria(request.getCategoria())
                .descricao(request.getDescricao())
            .preco(request.getPreco().doubleValue())
                .disponivel(true)
                .restaurante(restaurante)
                .build();

        Produto salvo = produtoService.cadastrar(produto);
        return ResponseEntity.status(201).body(toResponse(salvo));
    }

    @GetMapping("/restaurante/{restauranteId}")
    public List<ProdutoResponse> listarPorRestaurante(@PathVariable Long restauranteId) {
        return produtoService.buscarPorRestaurante(restauranteId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequest request) {
        Produto atualizado = Produto.builder()
                .nome(request.getNome())
                .categoria(request.getCategoria())
                .descricao(request.getDescricao())
            .preco(request.getPreco().doubleValue())
                .build();
        Produto salvo = produtoService.atualizar(id, atualizado);
        return ResponseEntity.ok(toResponse(salvo));
    }

    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Void> alterarDisponibilidade(@PathVariable Long id, @RequestParam boolean disponivel) {
        produtoService.alterarDisponibilidade(id, disponivel);
        return ResponseEntity.noContent().build();
    }

    // ADICIONAR: Listar todos os produtos
    @GetMapping
    public List<ProdutoResponse> listarTodos() {
        return produtoService.listarTodos().stream()
                 .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ADICIONAR: Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                 .map(this::toResponse)
                .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().<ProdutoResponse>build());
    }

    // ✅ ADICIONAR: Endpoint para deletar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ ALTERNATIVA: Se quiser usar inativação (soft delete)
    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        produtoService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar produtos por categoria
     * GET /api/produtos/categoria/{categoria}
     */
    @GetMapping("/categoria/{categoria}")
    public List<ProdutoResponse> buscarPorCategoria(@PathVariable String categoria) {
        return produtoService.buscarPorCategoria(categoria).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Busca produtos por nome
     * GET /api/produtos/buscar?nome={nome}
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutoResponse>> buscarPorNome(@RequestParam String nome) {
        try {
            List<Produto> produtos = produtoService.buscarPorNome(nome);
            
            List<ProdutoResponse> response = produtos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log do erro para debug
            System.err.println("Erro ao buscar produtos por nome: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getDescricao(),
                toBigDecimal(produto.getPreco()),
                produto.isDisponivel());
    }

    private BigDecimal toBigDecimal(Double valor) {
        return valor == null ? null : BigDecimal.valueOf(valor);
    }
}
