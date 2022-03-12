package com.softwalter.api.cliente.domain.usecase.usecaseimpl;

import com.softwalter.api.cliente.domain.repositories.ClienteRepository;
import com.softwalter.api.cliente.domain.usecase.ClienteService;
import com.softwalter.api.cliente.domain.usecase.mapper.ClienteMapper;
import com.softwalter.api.cliente.rest.dto.ClienteRequest;
import com.softwalter.api.cliente.rest.dto.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository userRepository;

    @Autowired
    ClienteMapper clienteMapper;

    public ClienteResponse execute(Pageable page){
        return clienteMapper.mapper(
                userRepository.findAll(page).getContent()
        );
    }

    @Override
    public ClienteResponse executePage(Pageable page) {
        return null;
    }

    @Override
    public ClienteResponse execute(Long id, ClienteRequest request) {
        return null;
    }
}
