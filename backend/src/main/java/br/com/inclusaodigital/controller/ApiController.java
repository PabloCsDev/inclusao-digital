package br.com.inclusaodigital.controller;

import br.com.inclusaodigital.model.*;
import br.com.inclusaodigital.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final CrudService service;
    public ApiController(CrudService service){this.service=service;}
    @GetMapping("/health") public Map<String,String> health(){return Map.of("status","ok");}
    @GetMapping("/dashboard") public Map<String,Object> dashboard(){return service.dashboard();}
    @GetMapping("/alunos") public List<Aluno> alunos(@RequestParam(required=false) String nome){return service.alunos(nome);}
    @GetMapping("/alunos/{id}") public Aluno aluno(@PathVariable Long id){return service.aluno(id);}
    @PostMapping("/alunos") public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody Aluno x){return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarAluno(x));}
    @PutMapping("/alunos/{id}") public Aluno editarAluno(@PathVariable Long id,@Valid @RequestBody Aluno x){Aluno atual=service.aluno(id);atual.setNome(x.getNome());atual.setCpf(x.getCpf());atual.setTelefone(x.getTelefone());atual.setEmail(x.getEmail());atual.setAtivo(x.isAtivo());atual.setObservacao(x.getObservacao());return service.salvarAluno(atual);}
    @DeleteMapping("/alunos/{id}") public ResponseEntity<Void> excluirAluno(@PathVariable Long id){service.excluirAluno(id);return ResponseEntity.noContent().build();}
    @GetMapping("/oficinas") public List<Oficina> oficinas(){return service.oficinas();}
    @GetMapping("/oficinas/{id}") public Oficina oficina(@PathVariable Long id){return service.oficina(id);}
    @PostMapping("/oficinas") public ResponseEntity<Oficina> criarOficina(@Valid @RequestBody Oficina x){return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarOficina(x));}
    @PutMapping("/oficinas/{id}") public Oficina editarOficina(@PathVariable Long id,@Valid @RequestBody Oficina x){Oficina atual=service.oficina(id);atual.setNome(x.getNome());atual.setDescricao(x.getDescricao());atual.setLocal(x.getLocal());atual.setCapacidade(x.getCapacidade());atual.setAtiva(x.isAtiva());return service.salvarOficina(atual);}
    @DeleteMapping("/oficinas/{id}") public ResponseEntity<Void> excluirOficina(@PathVariable Long id){service.excluirOficina(id);return ResponseEntity.noContent().build();}
    @GetMapping("/turmas") public List<Turma> turmas(){return service.turmas();}
    @GetMapping("/turmas/{id}") public Turma turma(@PathVariable Long id){return service.turma(id);}
    @PostMapping("/turmas") public ResponseEntity<Turma> criarTurma(@Valid @RequestBody Turma x){return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarTurma(x));}
    @PutMapping("/turmas/{id}") public Turma editarTurma(@PathVariable Long id,@Valid @RequestBody Turma x){Turma atual=service.turma(id);atual.setOficina(x.getOficina());atual.setNome(x.getNome());atual.setDataInicio(x.getDataInicio());atual.setDataFim(x.getDataFim());atual.setHorarioInicio(x.getHorarioInicio());atual.setHorarioFim(x.getHorarioFim());atual.setResponsavel(x.getResponsavel());atual.setAtiva(x.isAtiva());return service.salvarTurma(atual);}
    @DeleteMapping("/turmas/{id}") public ResponseEntity<Void> excluirTurma(@PathVariable Long id){service.excluirTurma(id);return ResponseEntity.noContent().build();}
    @GetMapping("/inscricoes") public List<Inscricao> inscricoes(@RequestParam(required=false) Long turmaId){return service.inscricoes(turmaId);}
    @GetMapping("/inscricoes/{id}") public Inscricao inscricao(@PathVariable Long id){return service.inscricao(id);}
    @PostMapping("/inscricoes") public ResponseEntity<Inscricao> criarInscricao(@RequestBody Inscricao x){return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarInscricao(x));}
    @PutMapping("/inscricoes/{id}") public Inscricao editarInscricao(@PathVariable Long id,@RequestBody Inscricao x){Inscricao atual=service.inscricao(id);atual.setStatus(x.getStatus());return service.salvarInscricaoEdicao(atual);}
    @DeleteMapping("/inscricoes/{id}") public ResponseEntity<Void> excluirInscricao(@PathVariable Long id){service.excluirInscricao(id);return ResponseEntity.noContent().build();}
    @GetMapping("/presencas") public List<Presenca> presencas(@RequestParam(required=false) Long inscricaoId){return service.presencas(inscricaoId);}
    @PostMapping("/presencas") public ResponseEntity<Presenca> criarPresenca(@RequestBody Presenca x){return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarPresenca(x));}
    @PutMapping("/presencas/{id}") public Presenca editarPresenca(@PathVariable Long id,@RequestBody Presenca x){Presenca atual=service.presencas(null).stream().filter(p->Objects.equals(p.getId(),id)).findFirst().orElseThrow();atual.setPresente(x.isPresente());atual.setData(x.getData());return service.salvarPresenca(atual);}
    @DeleteMapping("/presencas/{id}") public ResponseEntity<Void> excluirPresenca(@PathVariable Long id){service.excluirPresenca(id);return ResponseEntity.noContent().build();}
    @GetMapping("/computadores") public List<Computador> computadores(){return service.computadores();}
    @GetMapping("/computadores/{id}") public Computador computador(@PathVariable Long id){return service.computador(id);}
    @PostMapping("/computadores") public ResponseEntity<Computador> criarComputador(@Valid @RequestBody Computador x){return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarComputador(x));}
    @PutMapping("/computadores/{id}") public Computador editarComputador(@PathVariable Long id,@Valid @RequestBody Computador x){Computador atual=service.computador(id);atual.setIdentificacao(x.getIdentificacao());atual.setStatus(x.getStatus());atual.setObservacao(x.getObservacao());return service.salvarComputador(atual);}
    @DeleteMapping("/computadores/{id}") public ResponseEntity<Void> excluirComputador(@PathVariable Long id){service.excluirComputador(id);return ResponseEntity.noContent().build();}
}
