package java.Api_Trabalho.Api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.Api_Trabalho.Api.modelo.Time;
import java.Api_Trabalho.Api.repositorio.TimeRepositorio;


@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeRepositorio timeRepositorio;

    @GetMapping
    public List<Time> listar() {
        return timeRepositorio.findAll();
    }

    @PostMapping
    public ResponseEntity<Time> incluir(@RequestBody Time time) {
        Time salvo = timeRepositorio.save(time);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{idTime}")
    public ResponseEntity<Time> alterar(@PathVariable Long idTime, @RequestBody Time time) {
        Optional<Time> timeExistente = timeRepositorio.findById(idTime);
        if (timeExistente.isPresent()) {
            Time existente = timeExistente.get();

            // Atualiza os campos manualmente
            existente.setNome(time.getNome());
            existente.setCidade(time.getCidade());
            existente.setEstado(time.getEstado());
            existente.setPais(time.getPais());
            existente.setAnoFundacao(time.getAnoFundacao());
            existente.setEstadio(time.getEstadio());
            existente.setPatrocinador(time.getPatrocinador());
            existente.setCapacidadeEstadio(time.getCapacidadeEstadio());
            existente.setTreinador(time.getTreinador());

            Time atualizado = timeRepositorio.save(existente);
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idTime}")
    public ResponseEntity<Void> deletar(@PathVariable Long idTime) {
        if (timeRepositorio.existsById(idTime)) {
            timeRepositorio.deleteById(idTime);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idTime}")
    public ResponseEntity<Time> buscaTime(@PathVariable Long idTime) {
        return timeRepositorio.findById(idTime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

