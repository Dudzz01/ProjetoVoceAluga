package com.teamvocealuga.vocealuga.locacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/locacao")
public class LocacaoController
{
    @Autowired
    private LocacaoService locacaoService;

    @GetMapping("/{id}")

    public ResponseEntity<LocacaoDTO> findLocacaoById(@PathVariable Long id)
    {
        LocacaoDTO locacaoDTO = locacaoService.findLocacaoById(id);
        return ResponseEntity.ok().body(locacaoDTO);
    }

    @PostMapping
    public ResponseEntity<Void> createLocacao(@RequestBody LocacaoDTO locacaoDTO)
    {
        LocacaoDTO locacaoDTO1 = locacaoService.createLocacao(locacaoDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(locacaoDTO1.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocacao(@PathVariable Long id)
    {
        locacaoService.deleteLocacao(id);
        return ResponseEntity.noContent().build();
    }
}
