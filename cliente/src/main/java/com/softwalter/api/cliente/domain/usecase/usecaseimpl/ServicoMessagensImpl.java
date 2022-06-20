package com.softwalter.api.cliente.domain.usecase.usecaseimpl;

import com.softwalter.api.cliente.domain.entities.Cliente;
import com.softwalter.api.cliente.domain.usecase.ServicoMessagens;
import com.softwalter.api.cliente.utils.ExternalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ServicoMessagensImpl implements ServicoMessagens {

    private final ExternalService externalService;

    @Override
    public List<Cliente> enviarMessagem(Cliente cliente) {
        int COUNTER = 0;
        try {
            log.info("lancando exception COUNT =" +COUNTER);
            externalService.get();
            COUNTER++;
            log.info("nao cai no erro");
        } catch (Exception exception) {
            log.error("caiu no erro Erro: "+exception);
        }
        return List.of(cliente);
    }

//    @Retryable(
//            value = {RuntimeException.class},
//            maxAttempts = 4, backoff = @Backoff(2000))
    @Retryable(
            value = RuntimeException.class,
            maxAttemptsExpression = "${config.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${config.retry.maxDelay}")
    )
    public Cliente enviarMSN(Cliente cliente) {
        log.info("enviando cliente "+cliente);
        return externalService.get();
    }
}
