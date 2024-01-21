package com.teamvocealuga.vocealuga.devolucao;

import com.teamvocealuga.vocealuga.filial.Filial;
import com.teamvocealuga.vocealuga.filial.FilialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/devolucao")
public class DevolucaoController
{
    @Autowired
    private DevolucaoService devolucaoService;

    @GetMapping("/{id}")
    public ResponseEntity<DevolucaoDTO> findDevolucaoById(@PathVariable Long id)
    {
        DevolucaoDTO devolucaoDTO = devolucaoService.findDevolucaoById(id);
        return ResponseEntity.ok().body(devolucaoDTO);
    }

    @PostMapping
    public ResponseEntity<Void> createDevolucao(DevolucaoDTO devolucaoDTO)
    {
        devolucaoDTO = devolucaoService.createDevolucao(devolucaoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(devolucaoDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevolucao(@PathVariable Long id)
    {
        devolucaoService.deleteDevolucao(id);
        return ResponseEntity.noContent().build();
    }

}
