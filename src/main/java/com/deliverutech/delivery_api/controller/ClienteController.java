package com.deliverutech.delivery_api.controller;

import com.deliverutech.delivery_api.dto.request.ClienteRequest;
import com.deliverutech.delivery_api.model.Cliente;
import com.deliverutech.delivery_api.service.ClienteService;
import com.deliverutech.delivery_api.dto.response.ApiResponseWrapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    
    // Cadastrar nofvo cliente no banco
    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ClienteRequest clienteRequest) {
        try {
            log.info("Recebida solicitação para cadastrar cliente: {}", clienteRequest.getEmail());
            Cliente clienteSalvo = clienteService.cadastrar(clienteRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
        } catch (IllegalArgumentException e) {
            log.warn("Erro de validação ao cadastrar cliente: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            log.error("Erro interno ao cadastrar cliente: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado ao cadastrar cliente.");
        }
    }

    // Listar todos os clientes ativos
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        log.info("Recebida solicitação para listar clientes ativos");
        List<Cliente> clientes = clienteService.listarAtivos();
        return ResponseEntity.ok(clientes);
    }

    // Listar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<Cliente>> buscarPorId(@PathVariable Long id) {
        log.info("Recebida solicitação para buscar cliente por ID: {}", id);
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok(ApiResponseWrapper.success(cliente.get(), "Cliente encontrado"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponseWrapper.error("Cliente não encontrado."));
        }
    }

    // Buscar cliente por email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        log.info("Recebida solicitação para buscar cliente por email: {}", email);
        Optional<Cliente> cliente = clienteService.buscarPorEmail(email);
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar clientes por nome
    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> buscarPorNome(@RequestParam String nome) {
        log.info("Recebida solicitação para buscar clientes por nome: {}", nome);
        List<Cliente> clientes = clienteService.buscarPorNome(nome);
        return ResponseEntity.ok(clientes);
    }

    // Atualizar dados do cliente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest clienteRequest) {
        try {
            log.info("Recebida solicitação para atualizar cliente: {}", id);
            Cliente clienteAtualizado = clienteService.atualizar(id, clienteRequest);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (IllegalArgumentException e) {
            log.warn("Erro de validação ao atualizar cliente: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            log.error("Erro interno ao atualizar cliente: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado ao atualizar cliente.");
        }
    }

    // Inativar cliente (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            log.info("Recebida solicitação para inativar cliente: {}", id);
            clienteService.inativar(id);
            return ResponseEntity.ok("Cliente inativado com sucesso.");
        } catch (IllegalArgumentException e) {
            log.warn("Erro de validação ao inativar cliente: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            log.error("Erro interno ao inativar cliente: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado ao inativar cliente.");
        }
    }

    // Ativar/Desativar cliente (toggle status ativo)
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> toggleStatus(@PathVariable Long id) {
        try {
            log.info("Recebida solicitação para alternar status do cliente: {}", id);
            Cliente clienteAtualizado = clienteService.ativarDesativarCliente(id);

            String status = clienteAtualizado.getAtivo() ? "ativado" : "desativado";
            return ResponseEntity.ok()
                .body(Map.of(
                    "message", "Cliente " + status + " com sucesso.", 
                    "cliente", clienteAtualizado
                ));

        } catch (IllegalArgumentException e) {
            log.warn("Erro de validação ao alternar status do cliente: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            log.error("Erro interno ao alternar status do cliente: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro inesperado ao alternar status do cliente.");
        }
    }
}
