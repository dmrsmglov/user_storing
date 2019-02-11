package ru.damir.server.user_storing.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.damir.server.user_storing.dao.entities.Account;
import ru.damir.server.user_storing.dao.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> findAllUsers(int limit, int offset) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);

        return entityManager.createQuery(cq)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<User> findAllUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot);

        return entityManager.createQuery(cq)
                .getResultList();
    }

    public User findUserByAccountNumber(int accountNumber) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        Join<User, Account> accountJoin = userRoot.join("accounts");
        cq.select(userRoot).where(
                criteriaBuilder.equal(accountJoin.get("number"), accountNumber));

        return entityManager.createQuery(cq).getResultList().stream().findFirst().orElse(null);
    }

    public User findUserByEmailAndAccountNumber(String email, int accountNumber) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        Join<User, Account> accountJoin = userRoot.join("accounts");
        cq.select(userRoot).where(
                criteriaBuilder.equal(accountJoin.get("number"), accountNumber),
                criteriaBuilder.equal(userRoot.get("email"), email));

        return entityManager.createQuery(cq).getResultList().stream().findFirst().orElse(null);
    }
}
