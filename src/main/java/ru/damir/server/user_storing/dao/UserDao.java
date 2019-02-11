package ru.damir.server.user_storing.dao;

import org.springframework.stereotype.Component;
import ru.damir.server.user_storing.dao.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<User> findAllUsers(int limit, int offset) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);
        return entityManager.createQuery(cq)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

    }

    public User findUserByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);
        cq.where(cb.equal(userRoot.get("email"), email));
        return entityManager.createQuery(cq)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public User findUserByAccountNumber(int accountNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        return null;
    }

    public User findUserByEmailAndAccountNumber(String email, int accountNumber) {
        return null;
    }

}
