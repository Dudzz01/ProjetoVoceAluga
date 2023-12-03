package com.teamvocealuga.vocealuga.ordemdemanutencao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrdemDeManutencaoService
{
    @Autowired
    private OrdemDeManutencaoRepository ordemDeManutencaoRepository;

    public OrdemDeManutencaoDTO findOrdemManutencaoById(Long id)
    {
        Optional<OrdemDeManutencao> ordemDeManutencao = ordemDeManutencaoRepository.findById(id);
        ordemDeManutencao.orElseThrow(()->new RuntimeException("Erro ao procurar a ordem de manutencao"));

        OrdemDeManutencaoDTO ordemDeManutencaoDTO = ordemDeManutencao.get().converterOrdemDeManutencaoParaDTO();;
        return ordemDeManutencaoDTO;
    }

    @Transactional
    public OrdemDeManutencaoDTO createOrdemDeManutencao(OrdemDeManutencaoDTO ordemDeManutencaoDTO)
    {
        ordemDeManutencaoDTO.setId(null);
        OrdemDeManutencao ordemDeManutencao = ordemDeManutencaoDTO.converterDTOParaOrdemDeManutencao();
        ordemDeManutencao = ordemDeManutencaoRepository.save(ordemDeManutencao);
        ordemDeManutencaoDTO = ordemDeManutencao.converterOrdemDeManutencaoParaDTO();
        return ordemDeManutencaoDTO;
    }

    @Transactional
    public OrdemDeManutencaoDTO updatePrevisaoSaidaOrdemDeManutencao(OrdemDeManutencaoDTO ordemDeManutencaoDTO)
    {
        OrdemDeManutencaoDTO ordemDeManutencaoDTONew = findOrdemManutencaoById(ordemDeManutencaoDTO.getId());
        OrdemDeManutencao ordemDeManutencao = ordemDeManutencaoDTONew.converterDTOParaOrdemDeManutencao();
        ordemDeManutencao.setPrevisaoSaida(ordemDeManutencaoDTO.getPrevisaoSaida());
        ordemDeManutencao = ordemDeManutencaoRepository.save(ordemDeManutencao);
        ordemDeManutencaoDTO = ordemDeManutencao.converterOrdemDeManutencaoParaDTO();
        return ordemDeManutencaoDTO;
    }

    public void deleteOrdemDeManutencao(Long id)
    {
        findOrdemManutencaoById(id);
        try
        {
            ordemDeManutencaoRepository.deleteById(id);
        }catch (Exception ex)
        {
            throw new RuntimeException("Nao pode ser deletado pois nesta relacionado a alguma outra entidade sendo chave estrangeira");
        }
    }


}
