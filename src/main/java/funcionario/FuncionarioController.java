package funcionario;

import filial.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController
{
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private FilialService filialService;

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findFuncionarioById(@PathVariable Long id)
    {
        FuncionarioDTO funcionarioDTO = funcionarioService.findFuncionarioById(id);
        return ResponseEntity.ok().body(funcionarioDTO);
    }

    @GetMapping("/filial/{id}")
    public ResponseEntity<List<FuncionarioDTO>> findFuncionariosByFilialId(@PathVariable Long id)
    {
        filialService.findFilialById(id);
        List<FuncionarioDTO> funcionarioDTOList = funcionarioService.findFuncionariosByFilialId(id);
        return ResponseEntity.ok().body(funcionarioDTOList);
    }

    @PostMapping
    public ResponseEntity<Void> createFuncionario(@RequestBody FuncionarioDTO funcionarioDTO )
    {
        FuncionarioDTO funcionarioDTOCreated = funcionarioService.createFuncionario(funcionarioDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionarioDTOCreated.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/senha/{id}")
    public ResponseEntity<Void> updatePasswordFuncionario(@RequestBody FuncionarioDTO funcionarioDTO, @PathVariable Long id)
    {
        funcionarioDTO.setId(id);
        funcionarioService.updatePasswordFuncionario(funcionarioDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/funcao/{id}")
    public ResponseEntity<Void> updateFuncaoFuncionario(@RequestBody FuncionarioDTO funcionarioDTO, @PathVariable Long id)
    {
        funcionarioDTO.setId(id);
        funcionarioService.updateFuncaoFuncionario(funcionarioDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Void> updateStatusFuncionario(@RequestBody FuncionarioDTO funcionarioDTO, @PathVariable Long id)
    {
        funcionarioDTO.setId(id);
        funcionarioService.updateStatusFuncionario(funcionarioDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id)
    {
        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.noContent().build(); 
    }

}
