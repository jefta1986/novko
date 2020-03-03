package com.novko.security;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaUserDaoImpl implements JpaUserDao {


    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String userName) {
        return entityManager.createQuery("select u from User u where u.username = ?1", User.class).setParameter(1, userName).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long userId) {
        return entityManager.find(User.class, userId);
    }
}
