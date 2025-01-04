package controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import dao.FuncionarioDao;
import factory.Persistencia;
import javax.persistence.TypedQuery;
import model.Funcionario;

public class FuncionarioController {

    private FuncionarioDao funcionarioDao;
    EntityManager em = Persistencia.getEntityManager();

    public FuncionarioController() {
        EntityManager em = Persistencia.getEntityManager();
        this.funcionarioDao = new FuncionarioDao(em);
    }

    public void createFuncionario(Funcionario funcionario, String cpfRhLogado) throws Exception {
    if (funcionario.getCpf() == null || funcionario.getCpf().isEmpty()) {
        throw new Exception("CPF é obrigatório.");
    }
    if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
        throw new Exception("Nome é obrigatório.");
    }
    if (findByCpf(funcionario.getCpf()) != null) {
        throw new Exception("CPF já registrado no sistema.");
    }
    funcionarioDao.create(funcionario);
    registrarAcao(cpfRhLogado, "Cadastrou funcionário: " + funcionario.getNome());
}


    public void editFuncionario(Funcionario funcionario, String cpfRhLogado) throws Exception {
        funcionarioDao.update(funcionario);
        registrarAcao(cpfRhLogado, "Editou funcionário: " + funcionario.getNome());
    }

    private void registrarAcao(String cpfRh, String acao) {
        funcionarioDao.registrarAcao(cpfRh, acao);
    }

    public Funcionario loginFuncionario(String email, String senha) throws Exception {
        Funcionario funcionario = funcionarioDao.findByEmail(email);
        if (funcionario == null || !funcionario.getSenha().equals(senha)) {
            throw new Exception("Email ou senha inválidos.");
        }
        return funcionario;
    }

    public void demitirFuncionario(String cpf, String cpfRhLogado) throws Exception {
    Funcionario funcionario = funcionarioDao.findByCpf(cpf);
    if (funcionario == null) {
        throw new Exception("Funcionário não encontrado.");
    }
    if (!funcionario.isAtivo()) {
        throw new Exception("Funcionário já está desligado.");
    }

    funcionario.setAtivo(false);
    funcionario.setDataDesligamento(new Date());
    funcionarioDao.update(funcionario);

    registrarAcao(cpfRhLogado, "Demitido funcionário: " + funcionario.getNome());
}


    public Funcionario findByCpf(String cpf) {
        return funcionarioDao.findByCpf(cpf);
    }
    
    public List<Funcionario> findByNomeOuCpf(String nome, String cpf) {
    String jpql = "SELECT f FROM Funcionario f WHERE (:nome IS NULL OR f.nome LIKE :nome) AND (:cpf IS NULL OR f.cpf = :cpf)";
    TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
    query.setParameter("nome", nome != null ? "%" + nome + "%" : null);
    query.setParameter("cpf", cpf != null ? cpf : null);
    return query.getResultList();
}


    public List<Funcionario> getAllFuncionarios() {
        return funcionarioDao.findAll();
    }

    public List<Funcionario> buscarFuncionarios(String nome, String cpf) {
        return funcionarioDao.findByNomeOuCpf(nome, cpf);
    }

    public List<String> getHistoricoRH() {
        return funcionarioDao.getHistorico();
    }

    // Filtro de histórico
    public List<String> filtrarHistorico(String nome, String cpf) throws Exception {
        List<Funcionario> funcionarios = buscarFuncionarios(nome, cpf); // Busca pelos funcionários
        if (funcionarios.isEmpty()) {
            throw new Exception("Nenhum funcionário encontrado.");
        }

        // Filtra o histórico de acordo com os funcionários encontrados
        return funcionarioDao.getHistorico(funcionarios); // Chamando método de histórico filtrado
    }
    
}
