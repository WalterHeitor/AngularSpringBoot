package com.softwalter.api.cliente.domain.usecase.usecaseimpl;

import com.softwalter.api.cliente.domain.entities.Customer;
import com.softwalter.api.cliente.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl {

    @Autowired
    CustomerRepository repository;

    public Page<Customer> search(
            String searchTerm,
            int page,
            int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "name");

        return repository.search(
                searchTerm.toLowerCase(),
                pageRequest);
    }

    public Page<Customer> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "name");
        return new PageImpl<>(
                repository.findAll(),
                pageRequest, size);
    }
}
