package motorista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista,Long>
{
    List<Motorista> findByCliente_Id(Long id);
}
