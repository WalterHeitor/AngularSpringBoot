package com.softwalter.api.cliente.domain.usecase;

import com.softwalter.api.cliente.domain.entities.Cliente;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicoMessagens {

    @Retryable(value = RuntimeException.class,
            maxAttempts = 1,
            backoff = @Backoff(delay = 10))
    public List<Cliente> enviarMessagem(Cliente cliente) throws RuntimeException;

}
