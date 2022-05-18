package com.softwalter.api.cliente.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;
    @Column(nullable = false)
    private String nome;
    @Column
    private String email;
   // @Column(unique = true)
    private String cpf;
    @Column
    private String foneCelular;
    @Column(updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataCriacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataAtualizacao;
    private Boolean ativo;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return idPessoa != null && Objects.equals(idPessoa, cliente.idPessoa);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
