package com.softwalter.api.cliente.rest.controller;

import com.softwalter.api.cliente.domain.usecase.ClienteService;
import com.softwalter.api.cliente.rest.dto.ClientePageResponse;
import com.softwalter.api.cliente.rest.dto.ClienteRequest;
import com.softwalter.api.cliente.rest.dto.ClienteResponse;
import com.softwalter.api.cliente.domain.usecase.ClienteCrud;
import com.softwalter.api.cliente.utils.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<ClientePageResponse> execute(
            @RequestParam(value = "ativo", defaultValue = "", required = false) Boolean ativo,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return ResponseEntity.ok(clienteService.executePage(ativo, pageable));
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
