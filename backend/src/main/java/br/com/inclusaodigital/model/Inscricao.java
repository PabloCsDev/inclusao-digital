package br.com.inclusaodigital.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="inscricoes", uniqueConstraints=@UniqueConstraint(name="uk_inscricao_aluno_turma", columnNames={"aluno_id","turma_id"}))
public class Inscricao {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="aluno_id",nullable=false)
    private Aluno aluno;
    @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="turma_id",nullable=false)
    private Turma turma;
    @NotNull @Column(nullable=false)
    private LocalDate dataInscricao;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20)
    private StatusInscricao status=StatusInscricao.ATIVA;
    public enum StatusInscricao { ATIVA, CONCLUIDA, CANCELADA }
    public Long getId(){return id;} public Aluno getAluno(){return aluno;} public void setAluno(Aluno v){aluno=v;} public Turma getTurma(){return turma;} public void setTurma(Turma v){turma=v;} public LocalDate getDataInscricao(){return dataInscricao;} public void setDataInscricao(LocalDate v){dataInscricao=v;} public StatusInscricao getStatus(){return status;} public void setStatus(StatusInscricao v){status=v;}
}
