package app.dao;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@ComponentScan("app")
public class UserDAOImpl implements UserDAO{

    private final EntityManagerFactory entityManagerFactory;
    @Autowired
    public UserDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void create(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }
    @Override
    public List<User> read() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            em.find(User.class, 1);

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);

            TypedQuery<User> query = em.createQuery(cq);
            return query.getResultList();
        }
    }
    @Override
    public void update(User user, Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User updatedUser = em.find(User.class, id);

        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());

        em.merge(updatedUser);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public User getByID(Long id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.find(User.class, id);
        }
    }

    @Override
    public List<Long> getIdList() {
        return read().stream().map(User::getId).toList();
    }
}
