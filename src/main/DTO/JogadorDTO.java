package DTO;



import java.Api_Trabalho.Api.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import java.Api_Trabalho.Api.modelo.Time;
import java.util.Optional;

public interface TimeRepositorio extends JpaRepository<Time, Long> {
    Optional<Time> findByNome(String nome);
}.modelo.Jogador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorDTO {

    private Long idJogador;
    private String nome;
    private String apelido;
    private String posicao;
    private Integer idade;
    private String nacionalidade;
    private Integer numeroCamisa;
    private String clubeAtual; 


    public static JogadorDTO fromJogador(Jogador jogador) {
        JogadorDTO dto = new JogadorDTO();
        dto.setIdJogador(jogador.getIdJogador());
        dto.setNome(jogador.getNome());
        dto.setApelido(jogador.getApelido());
        dto.setPosicao(jogador.getPosicao());
        dto.setIdade(jogador.getIdade());
        dto.setNacionalidade(jogador.getNacionalidade());
        dto.setNumeroCamisa(jogador.getNumeroCamisa());
        if (jogador.getTime() != null) {
            dto.setClubeAtual(jogador.getTime().getNome());
        }
        return dto;
    }
}