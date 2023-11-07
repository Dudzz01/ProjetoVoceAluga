package filial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/filial")
public class FilialController
{
    @Autowired
    private FilialService filialService;

    @GetMapping("/{id}")
    public ResponseEntity<FilialDTO> findFilialById(@PathVariable Long id)
    {
        FilialDTO filialDTO = filialService.findFilialById(id);

        return ResponseEntity.ok().body(filialDTO);
    }

    @PostMapping
    public ResponseEntity<Void> createFilial(@RequestBody FilialDTO filialDTO)
    {

        FilialDTO filialDTO1 = filialService.createFilial(filialDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(filialDTO1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilial(@PathVariable Long id)
    {
        filialService.deleteFilial(id);
        return ResponseEntity.noContent().build();
    }
}
