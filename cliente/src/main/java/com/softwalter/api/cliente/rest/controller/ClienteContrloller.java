package com.softwalter.api.cliente.rest.controller;

import com.softwalter.api.cliente.rest.dto.ClienteRequest;
import com.softwalter.api.cliente.rest.dto.ClienteResponse;
import com.softwalter.api.cliente.domain.usecase.ClienteCrud;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/softwalter/api/clientes")
@AllArgsConstructor
public class ClienteContrloller {

    private ClienteCrud clienteCrud;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponse> cadastrarCliente(
            @RequestBody @Valid  ClienteRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        final ClienteResponse clienteResponse = clienteCrud.cadastrarCliente(request);
        UriComponents uriComponents =
                uriComponentsBuilder.path("/cadastrar/{id}").buildAndExpand(
                        clienteResponse.getIdPessoa());
        var local = uriComponents.toUri();
        return ResponseEntity.created(local).body(clienteResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteResponse> buscarCliente(@PathVariable String id) {
        return ResponseEntity.ok(clienteCrud.buscarClientePorId(Long.parseLong(id)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable String id) {

        clienteCrud.deletarClientePorId(Long.parseLong(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponse> atualizarClientePorId(
            @PathVariable String id,
            @RequestBody @Valid ClienteRequest clienteRequest
            ) {
        final ClienteResponse clienteResponse = clienteCrud.atualizarCliente(
                Long.parseLong(id),
                clienteRequest);
        return ResponseEntity.ok(clienteResponse);
    }
}
