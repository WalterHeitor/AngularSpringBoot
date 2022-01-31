package com.softwalter.api.cliente.controller.dto;

import com.softwalter.api.cliente.domain.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {
    private Long idPessoa;
    private String nome;
    private String cpf;
    private String foneCelular;
    private LocalDateTime dataCadastro;
    private String email;
    private Boolean ativo;
    private LocalDateTime dataAtualizacao;

    public static ClienteResponse toResponse(Cliente cliente){
        return ClienteResponse.builder()
                .idPessoa(cliente.getIdPessoa())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .foneCelular(cliente.getFoneCelular())
                .dataCadastro(cliente.getDatacriacao())
                .email(cliente.getEmail())
                .ativo(cliente.getAtivo())
                .dataAtualizacao(cliente.getDataAtualizacao())
                .build();
    }
}
