package br.com.inclusaodigital.repository;
import br.com.inclusaodigital.model.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface OficinaRepository extends JpaRepository<Oficina,Long>{ List<Oficina> findByAtivaTrueOrderByNome(); }
