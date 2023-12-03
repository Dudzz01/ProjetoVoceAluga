package com.teamvocealuga.vocealuga.transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    public List<TransacaoDTO> findTransacaoByClienteId(Long id)
    {
        List<Transacao> transacaoList = transacaoRepository.findByCliente_Id(id);
        List<TransacaoDTO> transacaoDTOList = new ArrayList<TransacaoDTO>();

        if(transacaoList.isEmpty() || transacaoList == null)
        {
            throw new RuntimeException("Erro ao encontrar as transacoes do cliente");
        }
        else
        {
            for(Transacao transacao: transacaoList)
            {
                transacaoDTOList.add(transacao.converterTransacaoParaDTO());
            }

            return transacaoDTOList;
        }
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
