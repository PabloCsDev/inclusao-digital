package br.com.inclusaodigital.repository;
import br.com.inclusaodigital.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface InscricaoRepository extends JpaRepository<Inscricao,Long>{ Optional<Inscricao> findByAlunoIdAndTurmaId(Long alunoId,Long turmaId); List<Inscricao> findByTurmaIdOrderByAlunoNome(Long turmaId); long countByStatus(Inscricao.StatusInscricao status); }
