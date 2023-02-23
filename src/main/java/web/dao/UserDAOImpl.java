package web.dao;

import org.springframework.stereotype.Repository;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;


    public void create(User user) {
        entityManager.persist(user);
        System.out.println("Пользователь с именем " + user.getName() + " успешно внесен в базу");
    }

    public void delete(int id) {
        entityManager.joinTransaction();
        Query query = entityManager.createQuery("delete from User u where u.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        System.out.println("Пользователь с ID " + id + " успешно удален из базы");
    }

    public User show(int id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id=:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    public void update(int id, User user) {
        entityManager.joinTransaction();
        System.out.println("#1");
        User userFromDB = show(id);
        System.out.println("#2");
        userFromDB.setAge(user.getAge());
        System.out.println("#3");
        userFromDB.setName(user.getName());
        System.out.println("#4");
        userFromDB.setSurname(user.getSurname());
        System.out.println("#5");
        entityManager.persist(userFromDB);
        System.out.println("Пользователь с именем " + user.getName() + " успешно обновлен в базе данных");
    }
}
