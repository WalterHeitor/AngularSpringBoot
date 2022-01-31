package com.softwalter.api.cliente.domain.usecase.usecaseimpl;

import com.softwalter.api.cliente.controller.dto.ClienteRequest;
import com.softwalter.api.cliente.controller.dto.ClienteResponse;
import com.softwalter.api.cliente.domain.entities.Cliente;
import com.softwalter.api.cliente.domain.repositories.ClienteRepository;
import com.softwalter.api.cliente.domain.usecase.ClienteCrud;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

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

    @Override
    public ClienteResponse atualizarCliente(Long id, ClienteRequest clienteRequest) {

        final Cliente clienteAtualizado = clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteRequest.getNome());
                    cliente.setCpf(clienteRequest.getCpf());
                    cliente.setEmail(clienteRequest.getEmail());
                    cliente.setFoneCelular(clienteRequest.getFoneCelular());
                    cliente.setDataAtualizacao(LocalDateTime.now());
                    return cliente;
//                    return Cliente.builder()
//                            .idPessoa(id)
//                            .nome(clienteRequest.getNome())
//                            .cpf(clienteRequest.getCpf())
//                            .email(clienteRequest.getEmail())
//                            .foneCelular(clienteRequest.getFoneCelular())
//                            .dataCriacao(cliente.getDataCriacao())
//                            .dataAtualizacao(LocalDateTime.now())
//                            .ativo(cliente.getAtivo())
//                            .build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        clienteRepository.save(clienteAtualizado);
        return ClienteResponse.toResponse(clienteAtualizado);
    }
}
