package br.com.inclusaodigital.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="computadores",uniqueConstraints=@UniqueConstraint(name="uk_computador_identificacao",columnNames="identificacao"))
public class Computador {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max=50) @Column(nullable=false,length=50)
    private String identificacao;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20)
    private StatusComputador status=StatusComputador.DISPONIVEL;
    @Size(max=500) @Column(length=500)
    private String observacao;
    public enum StatusComputador { DISPONIVEL, EM_USO, MANUTENCAO, INATIVO }
    public Long getId(){return id;} public String getIdentificacao(){return identificacao;} public void setIdentificacao(String v){identificacao=v;} public StatusComputador getStatus(){return status;} public void setStatus(StatusComputador v){status=v;} public String getObservacao(){return observacao;} public void setObservacao(String v){observacao=v;}
}
