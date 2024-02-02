package com.teamvocealuga.vocealuga.locacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        locacaoDTO.setId(null);
        Locacao locacao = locacaoDTO.converterDtoParaLocacao();
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
