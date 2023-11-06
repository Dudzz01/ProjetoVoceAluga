package cliente;


import filial.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController
{
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FilialService filialService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findClientById(@PathVariable Long id)
    {
        ClienteDTO clienteDTO = clienteService.findClienteById(id);
        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping("/filial/{id}")
    public ResponseEntity<List<ClienteDTO>> findClientesByFilialId(@PathVariable Long id)
    {
        filialService.findFilialById(id);
        List<ClienteDTO> clienteDTOList = clienteService.findClientesByFilialId(id);
        return ResponseEntity.ok().body(clienteDTOList);
    }

    @PostMapping
    public ResponseEntity<Void> createCliente(@RequestBody ClienteDTO clienteDTO)
    {
        ClienteDTO clienteDTONew = clienteService.createCliente(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTONew.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTelefoneCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id)
    {
        clienteDTO.setId(id);
        ClienteDTO clienteDTO1 = clienteService.updateTelefoneCliente(clienteDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTotalFidelidade(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id)
    {
        clienteDTO.setId(id);
        ClienteDTO clienteDTO1 = clienteService.updateTotalFidelidade(clienteDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id)
    {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
