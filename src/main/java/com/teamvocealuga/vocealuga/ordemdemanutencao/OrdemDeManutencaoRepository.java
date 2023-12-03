package com.teamvocealuga.vocealuga.ordemdemanutencao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemDeManutencaoRepository extends JpaRepository<OrdemDeManutencao,Long>
{
}
