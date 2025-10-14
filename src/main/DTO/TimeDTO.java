package DTO;

import java.Api_Trabalho.Api.modelo.Time;

import lombok.Data;

@Data
public class TimeDTO {
    private String nome;
    private String cidade;
    private String estado;
    private String pais;
    private Integer anoFundacao;
    private String estadio;
    private String patrocinador;
    private Integer capacidadeEstadio;
    private String treinador;

   public TimeDTO(Time time) {
        this.nome = time.getNome();
        this.cidade = time.getCidade();
        this.estado = time.getEstado();
        this.pais = time.getPais();
        this.anoFundacao = time.getAnoFundacao();
        this.estadio = time.getEstadio();
        this.patrocinador = time.getPatrocinador();
        this.capacidadeEstadio = time.getCapacidadeEstadio();
        this.treinador = time.getTreinador();
    }
}