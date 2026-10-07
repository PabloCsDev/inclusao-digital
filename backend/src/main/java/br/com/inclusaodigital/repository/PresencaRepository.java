package br.com.inclusaodigital.repository;
import br.com.inclusaodigital.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;
public interface PresencaRepository extends JpaRepository<Presenca,Long>{ Optional<Presenca> findByInscricaoIdAndData(Long inscricaoId,LocalDate data); List<Presenca> findByInscricaoIdOrderByDataDesc(Long inscricaoId); long countByPresenteTrue(); }
