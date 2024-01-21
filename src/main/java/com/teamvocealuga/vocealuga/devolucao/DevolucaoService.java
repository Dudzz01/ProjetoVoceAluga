package com.teamvocealuga.vocealuga.devolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DevolucaoService
{
    @Autowired
    private DevolucaoRepository devolucaoRepository;

    public DevolucaoDTO findDevolucaoById(Long idDevolucao)
    {
        Optional<Devolucao> devolucao = devolucaoRepository.findById(idDevolucao);

        if(devolucao.isEmpty())
        {
            throw new RuntimeException("Objeto Devolucao vazio");
        }
        else
        {
            return devolucao.get().converterDevolucaoParaDTO();
        }
    }

    @Transactional
    public DevolucaoDTO createDevolucao(DevolucaoDTO devolucaoDTO)
    {
        devolucaoDTO.setId(null);
        Devolucao devolucao = devolucaoDTO.converterDtoParaDevolucao();
        devolucao = devolucaoRepository.save(devolucao);
        return devolucao.converterDevolucaoParaDTO();
    }

    public void deleteDevolucao(Long id)
    {
        findDevolucaoById(id);

        try {
            devolucaoRepository.deleteById(id);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Essa emtidade (devolucao) nao pode ser deletada pois possui uma chave estrangeira com ela");
        }


    }
}
