package com.teamvocealuga.vocealuga.motorista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/motorista")
public class MotoristaController
{
    @Autowired
    private MotoristaService motoristaService;

    @GetMapping("/{id}")
    public ResponseEntity<MotoristaDTO> findMotoristaById(@PathVariable Long id)
    {
        MotoristaDTO motoristaDTO = motoristaService.findMotoristaById(id);
        return ResponseEntity.ok().body(motoristaDTO);
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<MotoristaDTO>> findMotoristasByClienteId(@PathVariable Long id)
    {
        List<MotoristaDTO> motoristaDTOList = motoristaService.findMotoristasByClientId(id);
        return ResponseEntity.ok().body(motoristaDTOList);
    }

    @PostMapping
    public ResponseEntity<Void> createMotorista(@RequestBody MotoristaDTO motoristaDTO)
    {
        MotoristaDTO motoristaDTO1 = motoristaService.createMotorista(motoristaDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(motoristaDTO1.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotorista(@PathVariable Long id)
    {
        motoristaService.deleteMotorista(id);
        return ResponseEntity.noContent().build();
    }
}
