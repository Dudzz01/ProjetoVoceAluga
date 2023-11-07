package filial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FilialService
{
    @Autowired
    private FilialRepository filialRepository;

    public FilialDTO findFilialById(Long idFilial)
    {

            Optional<Filial> filial = filialRepository.findById(idFilial);

            if(filial.isEmpty()) // como estou usando o Optional, a minha filial só pode ser vazia, não há chance de ser nula
            {
                throw new RuntimeException("Filial não encontrada");
            }
            else
            {
                return new FilialDTO(filial);
            }
    }
    @Transactional
    public FilialDTO createFilial(FilialDTO filialDTO)
    {
        filialDTO.setId(null);
        Filial filial = new Filial(filialDTO);
        filial = filialRepository.save(filial);
        return new FilialDTO(filial);
    }

   /* @Transactional
    public FilialDTO updateFilial(Long id)
    {
        FilialDTO filialDTO = findFilialById(id);
        Filial filial = new Filial(filialDTO);
        filial = filialRepository.save(filial);
        filialDTO = filial.converterFilialParaDto();
        return filialDTO; //Nao quero atualizar nada da filial

    }
    */
    public void deleteFilial(Long id)
    {
        findFilialById(id); //verificando se existe a filial que queremos excluir
        try
        {
            filialRepository.deleteById(id);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Nao é possivel excluir essa entidade pois ela está relacionada com outras entidades");
        }



    }






}
