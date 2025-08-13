package br.edu.ifpi.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;


import br.edu.ifpi.Model.Funcionario;

import br.edu.ifpi.JPAUtil;

public class FuncionarioDAO {

    public void salvar(Funcionario funcionario) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(funcionario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public Funcionario buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

 public Funcionario Login(String matricula, String senha) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
        // Validação inicial dos parâmetros
        if (matricula == null || matricula.isEmpty() || senha == null || senha.isEmpty()) {
            return null;
        }

        // JPQL com a função TRIM() para garantir que os espaços em branco não causem problemas
        String jpql = "SELECT f FROM Funcionario f WHERE TRIM(f.matricula) = :matricula AND TRIM(f.senha) = :senha";
        
        TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
        
        // Passamos os parâmetros sem o trim, pois a função já fará isso na consulta
        query.setParameter("matricula", matricula);
        query.setParameter("senha", senha);

        System.out.println("Debug: Executando consulta JPQL: " + jpql);
        
        Funcionario resultado = query.getSingleResult();
        System.out.println("Debug: Login bem-sucedido para: " + resultado.getMatricula());
        return resultado;
        
    } catch (NoResultException e) {
        System.out.println("Debug: Nenhum funcionário encontrado com as credenciais fornecidas");
        return null;
    } catch (Exception e) {
        System.out.println("Debug: Erro na consulta: " + e.getMessage());
        e.printStackTrace();
        return null;
    } finally {
        em.close();
    }
}

    // Método para criar um funcionário de teste
    public void criarFuncionarioTeste() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            Funcionario funcionario = new Funcionario();
            funcionario.setNome("Admin Teste");
            funcionario.setCpf("12345678901");
            funcionario.setMatricula("admin");
            funcionario.setSenha("123");
            funcionario.setCargo("Administrador");
            funcionario.setSalario(5000.0);
            
            em.persist(funcionario);
            transaction.commit();
            
            System.out.println("Funcionário de teste criado com sucesso!");
            System.out.println("Matricula: admin, Senha: 123");
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao criar funcionário de teste: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    
}
