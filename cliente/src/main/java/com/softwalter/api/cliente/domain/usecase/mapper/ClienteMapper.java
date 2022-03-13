package com.softwalter.api.cliente.domain.usecase.mapper;

import com.softwalter.api.cliente.domain.entities.Cliente;
import com.softwalter.api.cliente.rest.dto.ClientePageResponse;
import com.softwalter.api.cliente.rest.dto.ClienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {
    public ClienteResponse mapper(List<Cliente> content) {

        return null;
    }

    public ClientePageResponse mapperListResponse(Page<Cliente> clienteList) {
        final List<ClienteResponse> responseList = clienteList.stream().map(cliente ->
                        ClienteResponse
                                .builder()
                                .idPessoa(cliente.getIdPessoa())
                                .nome(cliente.getNome())
                                .email(cliente.getEmail())
                                .cpf(cliente.getCpf())
                                .foneCelular(cliente.getFoneCelular())
                                .ativo(cliente.getAtivo())
                                .dataCadastro(cliente.getDataCriacao())
                                .dataAtualizacao(cliente.getDataAtualizacao())
                                .build())
                .collect(Collectors.toList());
        return ClientePageResponse.builder()
                .data(responseList)
                .pageNo(clienteList.getNumber())
                .pageSize(clienteList.getSize())
                .totalElements(clienteList.getTotalElements())
                .totalPages(clienteList.getTotalPages())
                .last(clienteList.isLast())
                .build();
    }
}
