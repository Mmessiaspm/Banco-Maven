package br.edu.ifpi.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;
import br.edu.ifpi.Model.ContaPoupanca;
import br.edu.ifpi.Model.PessoaFisica;
import br.edu.ifpi.JPAUtil;

public class ContaPoupancaDAO {

    public void salvar(ContaPoupanca contaPoupanca) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(contaPoupanca);
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
    
    public ContaPoupanca buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ContaPoupanca.class, id);
        } finally {
            em.close();
        }
    }

    public ContaPoupanca buscarPorNumero(String numero) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Cria a consulta JPQL para buscar por número
            String jpql = "SELECT p FROM ContaPoupanca p WHERE p.numero = :numero";
            TypedQuery<ContaPoupanca> query = em.createQuery(jpql, ContaPoupanca.class);
            query.setParameter("numero", numero);
            
            // Retorna o resultado da consulta, ou null se não for encontrado
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }


        public ContaPoupanca buscarPorTitular(PessoaFisica pessoa) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM ContaPoupanca c WHERE c.titular = :titular";
            TypedQuery<ContaPoupanca> query = em.createQuery(jpql, ContaPoupanca.class);
            query.setParameter("titular", pessoa);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }


    public void update(ContaPoupanca contaPoupanca) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            // Merge atualiza a entidade se ela já existe no banco de dados
            em.merge(contaPoupanca);

            transaction.commit();
            System.out.println("ContaPoupanca atualizada com sucesso!");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao atualizar ContaPoupanca: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(ContaPoupanca contaPoupanca) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            // Verificar se a entidade está gerenciada pelo EntityManager
            ContaPoupanca contaGerenciada = em.find(ContaPoupanca.class, contaPoupanca.getNumero());
            if (contaGerenciada != null) {
                em.remove(contaGerenciada);
                transaction.commit();
                System.out.println("ContaPoupanca removida com sucesso!");
            } else {
                System.out.println("ContaPoupanca não encontrada para remoção.");
            }
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao remover ContaPoupanca: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    } 

    public void deleteById(String numero) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            // Verificar se a entidade está gerenciada pelo EntityManager
            ContaPoupanca contaGerenciada = em.find(ContaPoupanca.class, numero);
            if (contaGerenciada != null) {
                em.remove(contaGerenciada);
                transaction.commit();
                System.out.println("ContaPoupanca removida com sucesso!");
            } else {
                System.out.println("ContaPoupanca não encontrada para remoção.");
            }
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao remover ContaPoupanca: " + e.getMessage());
            e.printStackTrace();
    
}
    
        finally {
            em.close();
        }
    }
}