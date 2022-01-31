package com.softwalter.api.cliente.domain.usecase;

import com.softwalter.api.cliente.controller.dto.ClienteRequest;
import com.softwalter.api.cliente.controller.dto.ClienteResponse;

public interface ClienteCrud {
    ClienteResponse cadastrarCliente(ClienteRequest clienteRequest);
    ClienteResponse buscarClientePorId(Long idPessoa);
    void deletarClientePorId(Long idPessoa);
}
