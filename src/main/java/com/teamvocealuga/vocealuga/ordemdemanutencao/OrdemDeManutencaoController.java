package com.teamvocealuga.vocealuga.ordemdemanutencao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/ordemdemanutencao")
public class OrdemDeManutencaoController
{
    @Autowired
    private OrdemDeManutencaoService ordemDeManutencaoService;

    @GetMapping("/{id}")
    public ResponseEntity<OrdemDeManutencaoDTO> findOrdemDeManutencaoById(@PathVariable Long id)
    {
        OrdemDeManutencaoDTO ordemDeManutencaoDTO = ordemDeManutencaoService.findOrdemManutencaoById(id);
        return ResponseEntity.ok().body(ordemDeManutencaoDTO);
    }

    @PostMapping
    public ResponseEntity<Void> createOrdemDeManutencao(@RequestBody OrdemDeManutencaoDTO ordemDeManutencaoDTO)
    {
        OrdemDeManutencaoDTO ordemDeManutencaoDTONew = ordemDeManutencaoService.createOrdemDeManutencao(ordemDeManutencaoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ordemDeManutencaoDTONew.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePrevisaoSaida(@RequestBody OrdemDeManutencaoDTO ordemDeManutencaoDTO, @PathVariable Long id)
    {
       ordemDeManutencaoDTO.setId(id);
       ordemDeManutencaoService.updatePrevisaoSaidaOrdemDeManutencao(ordemDeManutencaoDTO);
       return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdemDeManutencao(@PathVariable Long id)
    {
        ordemDeManutencaoService.deleteOrdemDeManutencao(id);
        return ResponseEntity.noContent().build();
    }
}
