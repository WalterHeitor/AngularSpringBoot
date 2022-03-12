package com.softwalter.api.cliente.rest.controller;

import com.softwalter.api.cliente.domain.entities.Customer;
import com.softwalter.api.cliente.domain.usecase.usecaseimpl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerComtroller {

    @Autowired
    CustomerServiceImpl service;

    @GetMapping("/search")
    public Page<Customer> search(
            @RequestParam("searchTerm") String searchTerm,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size) {
        return service.search(searchTerm, page, size);

    }

    @GetMapping
    public Page<Customer> getAll() {
        return service.findAll();
    }
}
