package com.teamvocealuga.vocealuga.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>
{
    List<Cliente> findByFilial_Id(Long id);

    Cliente findByCpf(String cpf);

    Cliente findByTelefone(String telefone);
}
