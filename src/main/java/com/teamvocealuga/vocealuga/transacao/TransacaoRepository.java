package com.teamvocealuga.vocealuga.transacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long>
{
    List<Transacao> findByCliente_Id(Long id);
}
