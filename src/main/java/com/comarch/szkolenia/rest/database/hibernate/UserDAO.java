package com.comarch.szkolenia.rest.database.hibernate;

import com.comarch.szkolenia.rest.database.IUserRepository;
import com.comarch.szkolenia.rest.model.User;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO implements IUserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public User persist(User user) {
        Session session = this.sessionFactory.openSession();
        User result = null;
        try {
            session.beginTransaction();
            result = session.merge(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void delete(long id) {
        User user = new User();
        user.setId(id);
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
        } catch(Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM com.comarch.szkolenia.rest.model.User", User.class);
        List<User> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<User> getById(long id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session
                .createQuery("FROM com.comarch.szkolenia.rest.model.User WHERE id = :id", User.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getByAge(int age) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session
                .createQuery("FROM com.comarch.szkolenia.rest.model.User WHERE age = :age", User.class);
        query.setParameter("age", age);

        return query.getResultList();
    }
}
