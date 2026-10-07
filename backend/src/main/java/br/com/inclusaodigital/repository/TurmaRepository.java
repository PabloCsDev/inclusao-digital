package br.com.inclusaodigital.repository;
import br.com.inclusaodigital.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface TurmaRepository extends JpaRepository<Turma,Long>{ List<Turma> findByAtivaTrueOrderByDataInicioAsc(); long countByAtivaTrue(); }
