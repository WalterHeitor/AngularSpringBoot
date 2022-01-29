package com.softwalter.api.cliente.domain.repositories;

import com.softwalter.api.cliente.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
