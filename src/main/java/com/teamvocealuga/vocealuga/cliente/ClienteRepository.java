package com.teamvocealuga.vocealuga.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>
{
    List<Cliente> findByFilial_Id(Long id);
}
