package com.teamvocealuga.vocealuga.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long>
{
    List<Veiculo> findByFilial_Id(Long id);
}
