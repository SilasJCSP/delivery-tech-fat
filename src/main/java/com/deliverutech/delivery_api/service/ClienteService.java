package com.deliverutech.delivery_api.service;

import java.util.List;
import java.util.Optional;

import com.deliverutech.delivery_api.dto.request.ClienteRequest;
import com.deliverutech.delivery_api.model.Cliente;

public interface ClienteService {

    Cliente cadastrar(ClienteRequest clienteReuest);

    Optional<Cliente> buscarPorId(Long id);

    Optional<Cliente> buscarPorEmail(String email);

    List<Cliente> listarAtivos();

    List<Cliente> buscarPorNome(String nome);

    Cliente atualizar(Long id, ClienteRequest clienteRequest);

    void desativar(Long id);

    Cliente ativarDesativarCliente(Long id);

    void inativar(Long id);

}
