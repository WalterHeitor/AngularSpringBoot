package com.softwalter.api.cliente.domain.repositories;

import com.softwalter.api.cliente.domain.entities.Produto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {

    List<Produto> findAllByPreco(double preco, Pageable pageable);
}
