package com.softwalter.api.cliente.domain.usecase;

import com.softwalter.api.cliente.rest.dto.ClienteRequest;
import com.softwalter.api.cliente.rest.dto.ClienteResponse;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    ClienteResponse executePage(Pageable page);

    ClienteResponse execute(Long id, ClienteRequest request);
}
