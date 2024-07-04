package com.teamvocealuga.vocealuga.locacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao,Long>
{
    List<Locacao> findByMotoristaId(Long motoristaId);



}
