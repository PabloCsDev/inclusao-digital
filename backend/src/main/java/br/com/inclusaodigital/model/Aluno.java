package br.com.inclusaodigital.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "alunos", uniqueConstraints = @UniqueConstraint(name = "uk_aluno_cpf", columnNames = "cpf"))
public class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String nome;
    @Size(max = 14)
    @Column(length = 14)
    private String cpf;
    @Size(max = 20)
    @Column(length = 20)
    private String telefone;
    @Email @Size(max = 160)
    @Column(length = 160)
    private String email;
    @Column(nullable = false)
    private boolean ativo = true;
    @Size(max = 500)
    @Column(length = 500)
    private String observacao;
    public Long getId(){return id;} public String getNome(){return nome;} public void setNome(String v){nome=v;} public String getCpf(){return cpf;} public void setCpf(String v){cpf=v==null||v.isBlank()?null:v.trim();} public String getTelefone(){return telefone;} public void setTelefone(String v){telefone=v;} public String getEmail(){return email;} public void setEmail(String v){email=v==null||v.isBlank()?null:v.trim();} public boolean isAtivo(){return ativo;} public void setAtivo(boolean v){ativo=v;} public String getObservacao(){return observacao;} public void setObservacao(String v){observacao=v;}
}
