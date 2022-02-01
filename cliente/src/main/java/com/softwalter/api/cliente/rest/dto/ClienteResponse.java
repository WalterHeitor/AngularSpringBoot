package com.softwalter.api.cliente.rest.dto;

import com.softwalter.api.cliente.domain.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
                .dataCadastro(cliente.getDataCriacao())
                .email(cliente.getEmail())
                .ativo(cliente.getAtivo())
                .dataAtualizacao(cliente.getDataAtualizacao())
                .build();
    }
}
