package com.softwalter.api.cliente.integratios.kafka;



import com.softwalter.api.cliente.configuracao.TopicoClienteParams;
import com.softwalter.api.cliente.domain.entities.Cliente;
import com.sofwalter.topico.cliente.TopicoCliente;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@AllArgsConstructor
public class ClienteKafkaProducerImpl implements ClienteKafkaProducer{

    private TopicoClienteParams topicoClienteParams;
    private KafkaTemplate<String, TopicoCliente> topicoClienteKafkaTemplate;


    @Override
    public void enviarTopicoCliente(String idPessoa, Cliente cliente) {
        final String ERRO = "executionException ";
        try {
            final TopicoCliente topicoCliente =
                    TopicoCliente.newBuilder().setIdPessoa(cliente.getIdPessoa().toString()).build();
            topicoClienteKafkaTemplate.send(topicoClienteParams.getTopicoNome(), topicoCliente);
        } catch (Exception exception) {
            log.error(ERRO +exception);
        }
    }
}
