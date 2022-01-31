package com.softwalter.api.cliente.controller.dto;


import com.softwalter.api.cliente.domain.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequest {
    private String nome;
    private String cpf;
    private String foneCelular;
    private String email;

    public Cliente toCliente(){
        return Cliente.builder()
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .foneCelular(this.foneCelular)
                .build();
    }
}
