package br.com.inclusaodigital.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="turmas")
public class Turma {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false, fetch=FetchType.EAGER) @JoinColumn(name="oficina_id",nullable=false)
    private Oficina oficina;
    @NotBlank @Size(max=80) @Column(nullable=false,length=80)
    private String nome;
    @NotNull @Column(nullable=false)
    private LocalDate dataInicio;
    @NotNull @Column(nullable=false)
    private LocalDate dataFim;
    @NotNull @Column(nullable=false)
    private LocalTime horarioInicio;
    @NotNull @Column(nullable=false)
    private LocalTime horarioFim;
    @NotBlank @Size(max=120) @Column(nullable=false,length=120)
    private String responsavel;
    @Column(nullable=false)
    private boolean ativa=true;
    public Long getId(){return id;} public Oficina getOficina(){return oficina;} public void setOficina(Oficina v){oficina=v;} public String getNome(){return nome;} public void setNome(String v){nome=v;} public LocalDate getDataInicio(){return dataInicio;} public void setDataInicio(LocalDate v){dataInicio=v;} public LocalDate getDataFim(){return dataFim;} public void setDataFim(LocalDate v){dataFim=v;} public LocalTime getHorarioInicio(){return horarioInicio;} public void setHorarioInicio(LocalTime v){horarioInicio=v;} public LocalTime getHorarioFim(){return horarioFim;} public void setHorarioFim(LocalTime v){horarioFim=v;} public String getResponsavel(){return responsavel;} public void setResponsavel(String v){responsavel=v;} public boolean isAtiva(){return ativa;} public void setAtiva(boolean v){ativa=v;}
}
