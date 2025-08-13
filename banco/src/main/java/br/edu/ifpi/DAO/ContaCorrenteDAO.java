package br.edu.ifpi.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;

import br.edu.ifpi.Model.ContaCorrente;
import br.edu.ifpi.Model.PessoaFisica;
import br.edu.ifpi.Model.PessoaJuridica;
import br.edu.ifpi.JPAUtil;

public class ContaCorrenteDAO {

    public void salvar(ContaCorrente contaCorrente) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(contaCorrente);
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
    
    public ContaCorrente buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ContaCorrente.class, id);
        } finally {
            em.close();
        }
    }

    public ContaCorrente buscarPorNumero(String numero) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Cria a consulta JPQL para buscar por CPF
            String jpql = "SELECT p FROM ContaCorrente p WHERE p.numero = :numero";
            TypedQuery<ContaCorrente> query = em.createQuery(jpql, ContaCorrente.class);
            query.setParameter("numero", numero);
            
            // Retorna o resultado da consulta, ou null se não for encontrado
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public ContaCorrente buscarPorTitular(PessoaFisica pessoa) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM ContaCorrente c WHERE c.titular = :titular";
            TypedQuery<ContaCorrente> query = em.createQuery(jpql, ContaCorrente.class);
            query.setParameter("titular", pessoa);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    //sobrecarrega do método buscarPorTitular para PessoaJuridica
    public ContaCorrente buscarPorTitular(PessoaJuridica pessoa) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM ContaCorrente c WHERE c.titular = :titular";
            TypedQuery<ContaCorrente> query = em.createQuery(jpql, ContaCorrente.class);
            query.setParameter("titular", pessoa);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public void update(ContaCorrente contaCorrente) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            // Merge atualiza a entidade se ela já existe no banco de dados
            em.merge(contaCorrente);
            
            transaction.commit();
            System.out.println("ContaCorrente atualizada com sucesso!");
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao atualizar ContaCorrente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(ContaCorrente contaCorrente) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            // Verificar se a entidade está gerenciada pelo EntityManager
            ContaCorrente contaGerenciada = em.find(ContaCorrente.class, contaCorrente.getNumero());
            if (contaGerenciada != null) {
                em.remove(contaGerenciada);
                transaction.commit();
                System.out.println("ContaCorrente removida com sucesso!");
            } else {
                System.out.println("ContaCorrente não encontrada para remoção.");
            }
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao remover ContaCorrente: " + e.getMessage());
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
            
            ContaCorrente conta = em.find(ContaCorrente.class, numero);
            if (conta != null) {
                em.remove(conta);
                transaction.commit();
                System.out.println("ContaCorrente removida com sucesso!");
            } else {
                System.out.println("ContaCorrente não encontrada com o número: " + numero);
            }
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao remover ContaCorrente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
