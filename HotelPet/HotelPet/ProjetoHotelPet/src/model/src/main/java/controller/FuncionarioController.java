package controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import dao.FuncionarioDao;
import factory.Persistencia;
import model.Funcionario;

public class FuncionarioController {

    private FuncionarioDao funcionarioDao;

    public FuncionarioController() {
        EntityManager em = Persistencia.getEntityManager();
        this.funcionarioDao = new FuncionarioDao(em);
    }

    public void createFuncionario(Funcionario funcionario, String cpfRhLogado) throws Exception {
        try {
            funcionarioDao.create(funcionario);
            funcionarioDao.registrarAcao(cpfRhLogado, "Cadastrou funcionário: " + funcionario.getNome());
        } catch (Exception e) {
            e.printStackTrace();
            Funcionario check = funcionarioDao.findByCpf(funcionario.getCpf());
            if (check != null) {
                return; // Funcionário já existe
            }
            throw new Exception("Erro ao criar funcionário: " + e.getMessage());
        }
    }

    public void editFuncionario(Funcionario funcionario, String cpfRhLogado) throws Exception {
        // Verifica se existe outro funcionário com o mesmo email (exceto o próprio)
        Funcionario existente = funcionarioDao.findByEmail(funcionario.getEmail());
        if (existente != null && existente.getId() != funcionario.getId()) {
            throw new Exception("Email já está em uso por outro funcionário.");
        }

        // Se estiver tentando mudar cargo de um RH
        if (funcionario.getCargo().equalsIgnoreCase("Gestor de RH")) {
            Funcionario original = funcionarioDao.findByCpf(funcionario.getCpf());
            if (original != null && !original.getCargo().equals(funcionario.getCargo())) {
                throw new Exception("Não é possível alterar o cargo de um Gestor de RH.");
            }
        }

        // Atualiza explicitamente a senha
        Funcionario atual = funcionarioDao.findByCpf(funcionario.getCpf());
        if (!atual.getSenha().equals(funcionario.getSenha())) {
            System.out.println("Atualizando senha do funcionário");
            atual.setSenha(funcionario.getSenha());
        }
        
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
        if (!funcionario.isAtivo()) {
            throw new Exception("Funcionário inativo no sistema.");
        }
        return funcionario;
    }

    public void demitirFuncionario(String cpf, String cpfRhLogado) throws Exception {
        try {
            Funcionario funcionario = funcionarioDao.findByCpf(cpf);
            if (funcionario == null) {
                throw new Exception("Funcionário não encontrado.");
            }
            if (!funcionario.isAtivo()) {
                throw new Exception("Este funcionário já está inativo.");
            }

            funcionario.setAtivo(false);
            funcionario.setDataDesligamento(new Date());
            funcionarioDao.update(funcionario);
            funcionarioDao.registrarAcao(cpfRhLogado, "Demitiu funcionário: " + funcionario.getNome());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao demitir funcionário: " + e.getMessage());
        }
    }

    public Funcionario findByCpf(String cpf) {
        return funcionarioDao.findByCpf(cpf);
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

    public void testarConexao() {
        try {
            List<Funcionario> funcionarios = funcionarioDao.findAll();
            System.out.println("TestarConexao -> Funcionários encontrados: " + (funcionarios != null && !funcionarios.isEmpty()));
        } catch (Exception e) {
            System.out.println("Erro ao testar conexão: " + e.getMessage());
        }
    }
    
}
