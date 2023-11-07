package com.teamvocealuga.vocealuga.transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransacaoService
{
    @Autowired
    private TransacaoRepository transacaoRepository;

    public TransacaoDTO findTransacaoById(Long id)
    {
        Optional<Transacao> transacao = transacaoRepository.findById(id);

        transacao.orElseThrow(() -> new RuntimeException("Erro ao encontrar a transacao"));

        TransacaoDTO transacaoDTO = transacao.get().converterTransacaoParaDTO();
        return transacaoDTO;
    }
    @Transactional
    public TransacaoDTO createTransacao(TransacaoDTO transacaoDTO)
    {
        transacaoDTO.setId(null);
        Transacao transacao = transacaoDTO.converterDTOParaTransacao();
        transacao = transacaoRepository.save(transacao);
        transacaoDTO = transacao.converterTransacaoParaDTO();
        return transacaoDTO;
    }

    //NAO TENHO NADA PARA ATUALIZAR NA TRANSACAO

    public void deleteTransacao(Long id)
    {
        findTransacaoById(id);

        try
        {
            transacaoRepository.deleteById(id);
        }
        catch (Exception exception)
        {
            throw new RuntimeException("Nao da pra deletar pois transacao e uma chave estrangeira em outra entidade");
        }

    }
}
