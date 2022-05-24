package com.softwalter.api.cliente.utils;

import com.softwalter.api.cliente.domain.entities.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    public Cliente get() {
        throw new RuntimeException();
    }

}
