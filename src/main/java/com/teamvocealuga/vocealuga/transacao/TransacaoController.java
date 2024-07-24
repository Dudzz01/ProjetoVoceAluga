package com.teamvocealuga.vocealuga.transacao;

import com.teamvocealuga.vocealuga.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController
{
    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDTO> findTransacaoById(@PathVariable Long id)
    {
       TransacaoDTO transacaoDTO = transacaoService.findTransacaoById(id);
       return ResponseEntity.ok().body(transacaoDTO);
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<TransacaoDTO>> findTransacaoByClienteId(@PathVariable Long id)
    {
        clienteService.findClienteById(id);
        List<TransacaoDTO> transacaoDTOList = transacaoService.findTransacaoByClienteId(id);
        return ResponseEntity.ok().body(transacaoDTOList);
    }

    @GetMapping("/locacao/{id}")
    public ResponseEntity<TransacaoDTO> findTransacaoByLocacaoId(@PathVariable Long id)
    {

        TransacaoDTO transacaoDTO = transacaoService.findTransacaoByLocacaoId(id);

        if(transacaoDTO != null)
        {
            return ResponseEntity.ok().body(transacaoDTO);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }


    }

    @PostMapping
    public ResponseEntity<Void> createTransacao(@RequestBody TransacaoDTO transacaoDTO)
    {

        //FALTA VERIFICAR SE A LOCACAO EXISTE
        TransacaoDTO transacaoDTO1 = transacaoService.createTransacao(transacaoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transacaoDTO1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable Long id)
    {
        transacaoService.deleteTransacao(id);
        return ResponseEntity.noContent().build();
    }
}
