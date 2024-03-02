package com.teamvocealuga.vocealuga.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>
{
    List<Funcionario> findByFilial_Id(Long id);
    Optional<Funcionario> findByCpf(String cpf);


}
