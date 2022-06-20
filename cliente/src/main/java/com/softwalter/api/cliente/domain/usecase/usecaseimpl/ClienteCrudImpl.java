package com.softwalter.api.cliente.domain.usecase.usecaseimpl;

import com.softwalter.api.cliente.domain.usecase.ServicoMessagens;
import com.softwalter.api.cliente.integratios.kafka.ClienteKafkaProducer;
import com.softwalter.api.cliente.rest.dto.ClienteRequest;
import com.softwalter.api.cliente.rest.dto.ClienteResponse;
import com.softwalter.api.cliente.domain.entities.Cliente;
import com.softwalter.api.cliente.domain.repositories.ClienteRepository;
import com.softwalter.api.cliente.domain.usecase.ClienteCrud;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Log4j2
@Component
@AllArgsConstructor
public class ClienteCrudImpl implements ClienteCrud {

    private ClienteRepository clienteRepository;
    private ServicoMessagensImpl messagens;
    private ClienteKafkaProducer clienteKafkaProducer;



    @Override
    public ClienteResponse cadastrarCliente(ClienteRequest clienteRequest) {
        final Cliente cliente = clienteRepository.save(clienteRequest.toCliente());

        clienteKafkaProducer.enviarTopicoCliente(cliente.getIdPessoa().toString(), cliente);
//        try {
//            log.info("lancando exception");
//            messagens.enviarMSN(cliente);
//            log.info("nao cai no erro");
//        } catch (Exception exception) {
//            log.error("caiu no erro Erro: "+exception);
//        }
 //           servicoMessagens.enviarMessagem(cliente);
        return  ClienteResponse.toResponse(cliente);
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
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
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
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        clienteRepository.save(clienteAtualizado);
        return ClienteResponse.toResponse(clienteAtualizado);
    }
}
