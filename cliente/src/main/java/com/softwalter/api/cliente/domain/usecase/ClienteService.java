package com.softwalter.api.cliente.domain.usecase;

import com.softwalter.api.cliente.rest.dto.ClientePageResponse;
import com.softwalter.api.cliente.rest.dto.ClienteRequest;
import com.softwalter.api.cliente.rest.dto.ClienteResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    ClientePageResponse executePage(Boolean ativo, Pageable page);

    ClienteResponse execute(Long id, ClienteRequest request);
}
