package br.com.inclusaodigital.repository;
import br.com.inclusaodigital.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AlunoRepository extends JpaRepository<Aluno,Long>{ Optional<Aluno> findByCpf(String cpf); List<Aluno> findByNomeContainingIgnoreCaseOrderByNome(String nome); long countByAtivoTrue(); }
