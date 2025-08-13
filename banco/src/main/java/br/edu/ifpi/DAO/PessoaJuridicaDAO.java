package br.edu.ifpi.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.NoResultException;

import br.edu.ifpi.Model.PessoaJuridica;

import br.edu.ifpi.JPAUtil;

public class PessoaJuridicaDAO {

    public void salvar(PessoaJuridica pessoaJuridica) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(pessoaJuridica);
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
    
    public PessoaJuridica buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(PessoaJuridica.class, id);
        } finally {
            em.close();
        }
    }

    public PessoaJuridica buscarPorCnpj(String cnpj) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Cria a consulta JPQL para buscar por CPF
            String jpql = "SELECT p FROM PessoaJuridica p WHERE p.cnpj = :cnpj";
            TypedQuery<PessoaJuridica> query = em.createQuery(jpql, PessoaJuridica.class);
            query.setParameter("cnpj", cnpj);
            
            // Retorna o resultado da consulta, ou null se não for encontrado
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    
}
