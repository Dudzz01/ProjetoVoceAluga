package com.teamvocealuga.vocealuga.locacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class LocacaoService
{
    @Autowired
    private LocacaoRepository locacaoRepository;

    public LocacaoDTO findLocacaoById(Long id)
    {
        Optional<Locacao> locacao = locacaoRepository.findById(id);

        locacao.orElseThrow(() -> new RuntimeException("Locacao não encontrada"));

        LocacaoDTO locacaoDTO = locacao.get().converterLocacaoParaDTO();

        return locacaoDTO;
    }

    @Transactional
    public LocacaoDTO createLocacao(LocacaoDTO locacaoDTO)
    {
        boolean canCreateLocacao = true;
        locacaoDTO.setId(null);
        Locacao locacao = locacaoDTO.converterDtoParaLocacao();

        List<Locacao> locacaoListVerifyDates = locacaoRepository.findByMotoristaId(locacao.getMotorista().getId());

        if(!locacaoListVerifyDates.isEmpty())
        {
            for(Locacao locacaoExistente: locacaoListVerifyDates)
            {
                if(locacao.getDataInicio().after(locacaoExistente.getDataFim()))
                {
                    canCreateLocacao = true;
                }
                else
                {
                    canCreateLocacao = false;
                    break;
                }
            }
        }

        if(locacao.getDataInicio().before(locacao.getDataFim()))
        {
            canCreateLocacao = false;
        }

        if(!canCreateLocacao)
        {
            return null;
        }

        locacao = locacaoRepository.save(locacao);
        return locacao.converterLocacaoParaDTO();
    }

    public void deleteLocacao(Long id)
    {
        findLocacaoById(id);

        try
        {
            locacaoRepository.deleteById(id);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Tem chave estrangeira relacionada a locacao");
        }
    }

}
