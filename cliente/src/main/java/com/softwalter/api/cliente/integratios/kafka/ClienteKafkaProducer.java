package com.softwalter.api.cliente.integratios.kafka;

import com.softwalter.api.cliente.domain.entities.Cliente;

public interface ClienteKafkaProducer {

    void enviarTopicoCliente(String idPessoa, Cliente cliente);

}
