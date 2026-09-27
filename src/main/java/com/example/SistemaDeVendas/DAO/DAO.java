package com.example.SistemaDeVendas.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class DAO<T extends Serializable> {

    private Class<T> entidade;

    private EntityManagerFactory emf;

    public DAO(Class<T> entidade) {
        this.entidade = entidade;
        this.emf = Persistence.createEntityManagerFactory("pu-vendas");
    }

    public DAO() {
    }

    public void persisteNoDB(T object) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(object);
                tx.commit();
            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e;
            }
            System.out.println("PERSISTIDO NA DATABASE: \n" + object.toString() + "\n");
        } finally {
            em.close();
        }
    }

    public T selectNaDBbyID(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(entidade, id);
        } finally {
            em.close();
        }
    }

    public void update(T object) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.merge(object);
                tx.commit();
            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e;
            }
        } finally {
            em.close();
        }
    }

    public List<T> getAllObjects() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT o FROM " + entidade.getSimpleName() + " o", entidade).getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteObjectById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            T result = em.find(entidade, id);
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.remove(result);
                tx.commit();
            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e;
            }
        } finally {
            em.close();
        }
    }

    public T createCustomQUERY(String jpqlQUERY) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(jpqlQUERY, entidade).setMaxResults(1).getSingleResult();
        } finally {
            em.close();
        }
    }
}
