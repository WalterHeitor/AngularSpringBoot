package com.softwalter.api.cliente.configuracao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class TopicoClienteParams {

    @Value("${topico.nome}")
    private String topicoNome;

    @Value("${topico.client_id}")
    private String topicoClientId;

}
