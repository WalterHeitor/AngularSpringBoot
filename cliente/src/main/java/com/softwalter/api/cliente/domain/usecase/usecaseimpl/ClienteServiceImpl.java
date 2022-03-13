package com.softwalter.api.cliente.domain.usecase.usecaseimpl;

import com.softwalter.api.cliente.domain.entities.Cliente;
import com.softwalter.api.cliente.domain.repositories.ClienteRepository;
import com.softwalter.api.cliente.domain.usecase.ClienteService;
import com.softwalter.api.cliente.domain.usecase.mapper.ClienteMapper;
import com.softwalter.api.cliente.rest.dto.ClientePageResponse;
import com.softwalter.api.cliente.rest.dto.ClienteRequest;
import com.softwalter.api.cliente.rest.dto.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteMapper clienteMapper;

    public ClienteResponse execute(Pageable page){
        return clienteMapper.mapper(
                clienteRepository.findAll(page).getContent()
        );
    }

    @Override
    public ClientePageResponse executePage(Boolean ativo, Pageable page) {

//        final List<Cliente> clienteList = clienteRepository.findAllByAtivo(ativo, page);
        final Page<Cliente> clienteList = clienteRepository.findAllByAtivo(ativo, page);
        return clienteMapper.mapperListResponse(clienteList);
    }

    @Override
    public ClienteResponse execute(Long id, ClienteRequest request) {
        return null;
    }
}
