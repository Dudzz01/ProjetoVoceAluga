package com.teamvocealuga.vocealuga.motorista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista,Long>
{
    List<Motorista> findByCliente_Id(Long id);

    Optional<Motorista> findByCpf(String cpf);


}
