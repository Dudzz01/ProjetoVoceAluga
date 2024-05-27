package com.teamvocealuga.vocealuga.veiculo;

import com.teamvocealuga.vocealuga.filial.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController
{
    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private FilialService filialService;

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> findVeiculoById(@PathVariable Long id)
    {
        VeiculoDTO veiculoDTO = veiculoService.findVeiculoById(id);
        return ResponseEntity.ok().body(veiculoDTO);
    }

    @GetMapping("/filial/{id}")
    public ResponseEntity<List<VeiculoDTO>>findVeiculoByFilialId(@PathVariable Long id)
    {
        filialService.findFilialById(id);
        List<VeiculoDTO> veiculoDTOList = veiculoService.findVeiculosByFilialId(id);
        return ResponseEntity.ok().body(veiculoDTOList);
    }

    @PostMapping
    public ResponseEntity<Void> createVeiculo(@RequestBody VeiculoDTO veiculoDTO)
    {
         VeiculoDTO veiculoDTONew = veiculoService.createVeiculo(veiculoDTO);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculoDTONew.getId()).toUri();
         return ResponseEntity.created(uri).build();
    }

    @PutMapping("/att/{id}")
    public ResponseEntity<Void> updateStatusVeiculo(@RequestBody VeiculoDTO veiculoDTO, @PathVariable Long id)
    {
        veiculoDTO.setId(id);
        VeiculoDTO veiculoDTONew = veiculoService.updateStatus(veiculoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id)
    {
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
