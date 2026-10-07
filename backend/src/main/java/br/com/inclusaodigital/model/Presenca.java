package br.com.inclusaodigital.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="presencas", uniqueConstraints=@UniqueConstraint(name="uk_presenca_inscricao_data", columnNames={"inscricao_id","data"}))
public class Presenca {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="inscricao_id",nullable=false)
    private Inscricao inscricao;
    @NotNull @Column(nullable=false)
    private LocalDate data;
    @Column(nullable=false)
    private boolean presente;
    public Long getId(){return id;} public Inscricao getInscricao(){return inscricao;} public void setInscricao(Inscricao v){inscricao=v;} public LocalDate getData(){return data;} public void setData(LocalDate v){data=v;} public boolean isPresente(){return presente;} public void setPresente(boolean v){presente=v;}
}
