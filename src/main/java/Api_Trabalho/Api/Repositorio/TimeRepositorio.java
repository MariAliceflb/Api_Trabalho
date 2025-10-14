 package java.Api_Trabalho.Api.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import java.Api_Trabalho.Api.modelo.Time;
import java.util.Optional;

public interface TimeRepositorio extends JpaRepository<Time, Long> {
    Optional<Time> findByNome(String nome);
}