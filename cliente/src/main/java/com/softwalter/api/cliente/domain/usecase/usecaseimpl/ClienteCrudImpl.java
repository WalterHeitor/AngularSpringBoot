package com.softwalter.api.cliente.domain.usecase.usecaseimpl;

import com.softwalter.api.cliente.controller.dto.ClienteRequest;
import com.softwalter.api.cliente.controller.dto.ClienteResponse;
import com.softwalter.api.cliente.domain.repositories.ClienteRepository;
import com.softwalter.api.cliente.domain.usecase.ClienteCrud;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class ClienteCrudImpl implements ClienteCrud {
    private ClienteRepository clienteRepository;
    @Override
    public ClienteResponse cadastrarCliente(ClienteRequest clienteRequest) {
        return  ClienteResponse.toResponse(clienteRepository.save(clienteRequest.toCliente()));
    }

    @Override
    public ClienteResponse buscarClientePorId(Long idPessoa) {
        val clienteOptional = clienteRepository.findById(idPessoa);
        if (clienteOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ClienteResponse.toResponse(clienteOptional.get());
    }

    @Override
    public void deletarClientePorId(Long idPessoa) {
        clienteRepository.findById(idPessoa)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
