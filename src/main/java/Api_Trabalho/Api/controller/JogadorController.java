package java.Api_Trabalho.Api.controller;

import java.Api_Trabalho.Api.modelo.jogador;
import java.Api_Trabalho.Api.modelo.time;
import java.Api_Trabalho.Api.repositorio.JogadorRepositorio;
import java.Api_Trabalho.Api.repositorio.TimeRepositorio;
import DTO.JogadorDTO; 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    private JogadorRepositorio jogadorRepositorio;
    @Autowired
    private TimeRepositorio timeRepositorio;

    @GetMapping
    public List<JogadorDTO> listar() {
        List<Jogador> jogadores = jogadorRepositorio.findAll();
        return jogadores.stream()
            .map(JogadorDTO::fromJogador)
            .toList();
    }

    @GetMapping("/{idJogador}")
    public JogadorDTO buscaJogador(@PathVariable Long idJogador){
        Optional<Jogador> jogadorOpt = jogadorRepositorio.findById(idJogador);
        if (jogadorOpt.isPresent()) {
            return JogadorDTO.fromJogador(jogadorOpt.get());
        } else {
            throw new RuntimeException("Jogador não encontrado: " + idJogador);
        }
    }

 @PostMapping
public ResponseEntity<JogadorDTO> incluir(@RequestBody Jogador jogador) {
    if (jogador.getTime() != null && jogador.getTime().getNome() != null) {
        Optional<Time> timeOpt = timeRepositorio.findByNome(jogador.getTime().getNome());
        if (timeOpt.isPresent()) {
            jogador.setTime(timeOpt.get());
            Jogador salvo = jogadorRepositorio.save(jogador);

            
            return ResponseEntity
                    .status(201)
                    .body(JogadorDTO.fromJogador(salvo));
        } else {
            throw new RuntimeException("Time não encontrado: " + jogador.getTime().getNome());
        }
    } else {
        throw new RuntimeException("Nome do time não informado.");
    }
}


    @PutMapping("/{idJogador}")
    public void alterar(@PathVariable Long idJogador, @RequestBody Jogador jogador){
        Optional<Jogador> jogadorExistente = jogadorRepositorio.findById(idJogador);
        if (jogadorExistente.isPresent()) {
            Jogador j = jogadorExistente.get();
            j.setNome(jogador.getNome());
            j.setApelido(jogador.getApelido());
            j.setPosicao(jogador.getPosicao());
            j.setIdade(jogador.getIdade());
            j.setNacionalidade(jogador.getNacionalidade());
            j.setNumeroCamisa(jogador.getNumeroCamisa());

            if (jogador.getTime() != null && jogador.getTime().getNome() != null) {
                Optional<Time> timeOpt = timeRepositorio.findByNome(jogador.getTime().getNome());
                if (timeOpt.isPresent()) {
                    j.setTime(timeOpt.get());
                } else {
                    throw new RuntimeException("Time não encontrado: " + jogador.getTime().getNome());
                }
            }
            jogadorRepositorio.save(j);
        } else {
            throw new RuntimeException("Jogador não encontrado: " + idJogador);
        }
    }

    @DeleteMapping("/{idJogador}")
    public ResponseEntity<Void> deletar(@PathVariable Long idJogador) {
        if (jogadorRepositorio.existsById(idJogador)) {
            jogadorRepositorio.deleteById(idJogador);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}
