package com.teamvocealuga.vocealuga.transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transacao")
public class TransacaoController
{
    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDTO> findTransacaoById(@PathVariable Long id)
    {
       TransacaoDTO transacaoDTO = transacaoService.findTransacaoById(id);
       return ResponseEntity.ok().body(transacaoDTO);
    }

    @PostMapping
    public ResponseEntity<Void> createTransacao(@RequestBody TransacaoDTO transacaoDTO)
    {
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
