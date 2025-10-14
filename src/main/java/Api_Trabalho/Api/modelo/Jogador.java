package java.Api_Trabalho.Api.modelo;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@AllArgsConstructor
@Data
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJogador;

    private String nome;
    private String apelido;
    private String posicao;
    private Integer idade;
    private String nacionalidade;
    private Integer numeroCamisa;
    
    @ManyToOne
    @JoinColumn(name = "id_time")
    private Time time;
}
