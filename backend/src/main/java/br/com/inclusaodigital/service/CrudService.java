package br.com.inclusaodigital.service;

import br.com.inclusaodigital.exception.*;
import br.com.inclusaodigital.model.*;
import br.com.inclusaodigital.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class CrudService {
    private final AlunoRepository alunos; private final OficinaRepository oficinas; private final TurmaRepository turmas; private final InscricaoRepository inscricoes; private final PresencaRepository presencas; private final ComputadorRepository computadores;
    public CrudService(AlunoRepository a,OficinaRepository o,TurmaRepository t,InscricaoRepository i,PresencaRepository p,ComputadorRepository c){alunos=a;oficinas=o;turmas=t;inscricoes=i;presencas=p;computadores=c;}
    public List<Aluno> alunos(String nome){return nome==null||nome.isBlank()?alunos.findAll(org.springframework.data.domain.Sort.by("nome")):alunos.findByNomeContainingIgnoreCaseOrderByNome(nome);}
    public Aluno aluno(Long id){return alunos.findById(id).orElseThrow(()->new ResourceNotFoundException("Aluno não encontrado."));}
    public Aluno salvarAluno(Aluno x){if(x.getCpf()!=null&&!x.getCpf().isBlank()){alunos.findByCpf(x.getCpf()).filter(a->!Objects.equals(a.getId(),x.getId())).ifPresent(a->{throw new BusinessException("CPF já cadastrado.");});}return alunos.save(x);}
    public void excluirAluno(Long id){alunos.delete(aluno(id));}
    public List<Oficina> oficinas(){return oficinas.findAll(org.springframework.data.domain.Sort.by("nome"));}
    public Oficina oficina(Long id){return oficinas.findById(id).orElseThrow(()->new ResourceNotFoundException("Oficina não encontrada."));}
    public Oficina salvarOficina(Oficina x){return oficinas.save(x);} public void excluirOficina(Long id){oficinas.delete(oficina(id));}
    public List<Turma> turmas(){return turmas.findAll(org.springframework.data.domain.Sort.by("dataInicio"));}
    public Turma turma(Long id){return turmas.findById(id).orElseThrow(()->new ResourceNotFoundException("Turma não encontrada."));}
    public Turma salvarTurma(Turma x){if(x.getOficina()==null||x.getOficina().getId()==null)throw new BusinessException("Selecione uma oficina.");if(x.getDataFim().isBefore(x.getDataInicio()))throw new BusinessException("A data final não pode ser anterior à data inicial.");if(x.getHorarioFim().isBefore(x.getHorarioInicio()))throw new BusinessException("O horário final não pode ser anterior ao inicial.");x.setOficina(oficina(x.getOficina().getId()));return turmas.save(x);} public void excluirTurma(Long id){turmas.delete(turma(id));}
    public List<Inscricao> inscricoes(Long turmaId){return turmaId==null?inscricoes.findAll():inscricoes.findByTurmaIdOrderByAlunoNome(turmaId);}
    @Transactional public Inscricao salvarInscricao(Inscricao x){if(x.getAluno()==null||x.getAluno().getId()==null||x.getTurma()==null||x.getTurma().getId()==null)throw new BusinessException("Selecione o aluno e a turma.");Aluno a=aluno(x.getAluno().getId());Turma t=turma(x.getTurma().getId());if(inscricoes.findByAlunoIdAndTurmaId(a.getId(),t.getId()).isPresent())throw new BusinessException("Aluno já inscrito nesta turma.");long vagas=inscricoes.findByTurmaIdOrderByAlunoNome(t.getId()).stream().filter(i->i.getStatus()!=Inscricao.StatusInscricao.CANCELADA).count();if(vagas>=t.getOficina().getCapacidade())throw new BusinessException("A turma atingiu a capacidade máxima.");x.setAluno(a);x.setTurma(t);if(x.getDataInscricao()==null)x.setDataInscricao(LocalDate.now());return inscricoes.save(x);}
    public Inscricao inscricao(Long id){return inscricoes.findById(id).orElseThrow(()->new ResourceNotFoundException("Inscrição não encontrada."));}
    public Inscricao salvarInscricaoEdicao(Inscricao x){return inscricoes.save(x);}
    public void excluirInscricao(Long id){inscricoes.delete(inscricao(id));}
    public List<Presenca> presencas(Long inscricaoId){return inscricaoId==null?presencas.findAll():presencas.findByInscricaoIdOrderByDataDesc(inscricaoId);}
    @Transactional public Presenca salvarPresenca(Presenca x){if(x.getInscricao()==null||x.getInscricao().getId()==null)throw new BusinessException("Selecione a inscrição.");Inscricao i=inscricao(x.getInscricao().getId());if(x.getData()==null)x.setData(LocalDate.now());presencas.findByInscricaoIdAndData(i.getId(),x.getData()).filter(p->!Objects.equals(p.getId(),x.getId())).ifPresent(p->{throw new BusinessException("Já existe uma presença para esta inscrição nesta data.");});x.setInscricao(i);return presencas.save(x);}
    public void excluirPresenca(Long id){if(!presencas.existsById(id))throw new ResourceNotFoundException("Registro de presença não encontrado.");presencas.deleteById(id);}
    public List<Computador> computadores(){return computadores.findAll(org.springframework.data.domain.Sort.by("identificacao"));}
    public Computador computador(Long id){return computadores.findById(id).orElseThrow(()->new ResourceNotFoundException("Computador não encontrado."));}
    public Computador salvarComputador(Computador x){return computadores.save(x);} public void excluirComputador(Long id){computadores.delete(computador(id));}
    public Map<String,Object> dashboard(){return Map.of("alunos",alunos.count(),"alunosAtivos",alunos.countByAtivoTrue(),"oficinas",oficinas.count(),"turmasAtivas",turmas.countByAtivaTrue(),"inscricoes",inscricoes.countByStatus(Inscricao.StatusInscricao.ATIVA),"presencas",presencas.count(),"presencasConfirmadas",presencas.countByPresenteTrue(),"computadores",computadores.count(),"computadoresDisponiveis",computadores.countByStatus(Computador.StatusComputador.DISPONIVEL));}
}
