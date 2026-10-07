package br.com.inclusaodigital.repository;
import br.com.inclusaodigital.model.Computador;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ComputadorRepository extends JpaRepository<Computador,Long>{ long countByStatus(Computador.StatusComputador status); }
