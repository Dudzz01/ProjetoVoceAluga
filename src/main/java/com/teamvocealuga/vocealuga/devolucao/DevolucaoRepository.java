package com.teamvocealuga.vocealuga.devolucao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao,Long>
{

    List<Devolucao> findByLocacao_Id(Long id);

    //SELECT * FROM Devolucao WHERE locacao_id = 2;

}
