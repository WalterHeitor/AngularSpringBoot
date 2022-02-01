package com.softwalter.api.cliente.rest.exception;

import lombok.Getter;

import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> erros;

    public ApiErrors(List<String> erros) {
        this.erros = erros;
    }

    public ApiErrors(String menssage) {
        this.erros = List.of(menssage);
    }

}
