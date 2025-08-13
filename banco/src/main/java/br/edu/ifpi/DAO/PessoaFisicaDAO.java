package br.edu.ifpi.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;

import br.edu.ifpi.Model.PessoaFisica;

import br.edu.ifpi.JPAUtil;

public class PessoaFisicaDAO {

    public void salvar(PessoaFisica pessoaFisicao) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(pessoaFisicao);
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
    
    public PessoaFisica buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(PessoaFisica.class, id);
        } finally {
            em.close();
        }
    }

    public PessoaFisica buscarPorCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Cria a consulta JPQL para buscar por CPF
            String jpql = "SELECT p FROM PessoaFisica p WHERE p.cpf = :cpf";
            TypedQuery<PessoaFisica> query = em.createQuery(jpql, PessoaFisica.class);
            query.setParameter("cpf", cpf);
            
            // Retorna o resultado da consulta, ou null se não for encontrado
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    
}
