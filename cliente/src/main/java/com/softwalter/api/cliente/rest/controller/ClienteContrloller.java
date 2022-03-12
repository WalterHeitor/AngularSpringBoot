package com.softwalter.api.cliente.rest.controller;

import com.softwalter.api.cliente.domain.usecase.ClienteService;
import com.softwalter.api.cliente.rest.dto.ClienteRequest;
import com.softwalter.api.cliente.rest.dto.ClienteResponse;
import com.softwalter.api.cliente.domain.usecase.ClienteCrud;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponse> cadastrarCliente(
            @RequestBody @Valid ClienteRequest request,
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



    @GetMapping("/listclientes")
    @Cacheable("cliente")
    public ResponseEntity execute(
            @PageableDefault(sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10) Pageable page) {

        return ResponseEntity.ok(clienteService.executePage(page));
    }

//    @PutMapping("/{id}")
//    @CacheEvict(value = "users", allEntries = true)
//    public ResponseEntity execute(@PathVariable Long id,
//                                  @RequestBody @Valid ClienteRequest request) {
//
//        ClienteResponse response = clienteService.execute(id, request);
//
//        return ResponseEntity.ok(response);
//    }

}
