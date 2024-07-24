package com.teamvocealuga.vocealuga.motorista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista,Long>
{
    List<Motorista> findByCliente_Id(Long id);

    Optional<Motorista> findByCpfAndCnhAndCliente_Id(String cpf, String cnh, Long cliente_id);

    Optional<Motorista> findByCnh(String cnh);

<<<<<<< Updated upstream
    Optional<Motorista> findByCpf(String cnh);

    Optional<Motorista> findByCpfAndCnh(String cpf, String cnh);
=======
    Optional<Motorista> findByCpfMotoristaAndCnhMotoristaAndClienteId(String cpfMotorista, String cnhMotorista, Long clienteId);
    boolean existsByCpfMotoristaAndCnhMotorista(String cpfMotorista, String cnhMotorista);
    boolean existsByCpfMotoristaAndCnhMotoristaNot(String cpfMotorista, String cnhMotorista);
    boolean existsByCnhMotoristaAndCpfMotoristaNot(String cnhMotorista, String cpfMotorista);
>>>>>>> Stashed changes


}
