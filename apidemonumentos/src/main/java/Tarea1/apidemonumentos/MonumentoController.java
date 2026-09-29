package Tarea1.apidemonumentos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/monument")
public class MonumentoController {

    private final MonumentoRepository monumentoRepository;

    @GetMapping
    public ResponseEntity<List<Monumento>> getAllMonumentos() {
        List<Monumento> result = monumentoRepository.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 NOT FOUND
        }
        return ResponseEntity.ok(result); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monumento> getMonumentoById(@PathVariable Long id) {
        return ResponseEntity.of(monumentoRepository.findById(id)); // 200 OK o 404 NOT FOUND
    }

    @PostMapping
    public ResponseEntity<Monumento> createMonumento(@RequestBody Monumento monumento) {
        if (StringUtils.hasText(monumento.getNombreMonumento())) {
            return ResponseEntity.status(201).body(monumentoRepository.save(monumento)); // 201 CREATED
        }
        return ResponseEntity.badRequest().build(); // 400 BAD REQUEST
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monumento> updateMonumento(
            @PathVariable Long id,
            @RequestBody Monumento monumento) {

        if (!StringUtils.hasText(monumento.getNombreMonumento())) {
            return ResponseEntity.badRequest().build(); // 400 BAD REQUEST
        }

        return monumentoRepository.findById(id)
                .map(m -> {
                    m.setCodigoPais(monumento.getCodigoPais());
                    m.setNombrePais(monumento.getNombrePais());
                    m.setNombreCiudad(monumento.getNombreCiudad());
                    m.setLatitud(monumento.getLatitud());
                    m.setLongitud(monumento.getLongitud());
                    m.setNombreMonumento(monumento.getNombreMonumento());
                    m.setDescripcion(monumento.getDescripcion());
                    m.setUrlFoto(monumento.getUrlFoto());
                    return ResponseEntity.ok(monumentoRepository.save(m)); // 200 OK
                })
                .orElse(ResponseEntity.notFound().build()); // 404 NOT FOUND
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonumento(@PathVariable Long id) {
        monumentoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }
}
