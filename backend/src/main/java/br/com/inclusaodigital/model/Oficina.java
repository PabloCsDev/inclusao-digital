package br.com.inclusaodigital.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "oficinas")
public class Oficina {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 120) @Column(nullable=false,length=120)
    private String nome;
    @Size(max = 1000) @Column(length=1000)
    private String descricao;
    @NotBlank @Size(max = 160) @Column(nullable=false,length=160)
    private String local;
    @NotNull @Min(1) @Column(nullable=false)
    private Integer capacidade;
    @Column(nullable=false)
    private boolean ativa = true;
    public Long getId(){return id;} public String getNome(){return nome;} public void setNome(String v){nome=v;} public String getDescricao(){return descricao;} public void setDescricao(String v){descricao=v;} public String getLocal(){return local;} public void setLocal(String v){local=v;} public Integer getCapacidade(){return capacidade;} public void setCapacidade(Integer v){capacidade=v;} public boolean isAtiva(){return ativa;} public void setAtiva(boolean v){ativa=v;}
}
