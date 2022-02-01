package com.softwalter.api.cliente.rest.dto;


import com.softwalter.api.cliente.domain.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequest {
    @NotBlank
    private String nome;
    @CPF
    private String cpf;
    private String foneCelular;
    private String email;

    public Cliente toCliente(){
        return Cliente.builder()
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .foneCelular(this.foneCelular)
                .dataCriacao(LocalDateTime.now())
                .ativo(true)
                .build();
    }
}
