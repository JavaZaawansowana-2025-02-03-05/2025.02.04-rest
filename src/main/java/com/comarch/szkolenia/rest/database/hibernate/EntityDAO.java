package com.comarch.szkolenia.rest.database.hibernate;

import com.comarch.szkolenia.rest.model.EntityParent;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EntityDAO {
    private final SessionFactory sessionFactory;

    public void persistParent(EntityParent parent) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.persist(parent);
        session.getTransaction().commit();
    }

    public EntityParent getParentById(Long id) {
        Session session = this.sessionFactory.openSession();
        Query<EntityParent> query = session
                .createQuery("FROM com.comarch.szkolenia.rest.model.EntityParent WHERE id = :id",
                        EntityParent.class);
        query.setParameter("id", id);

        EntityParent ep = query.getSingleResult();
        //session.close();
        return ep;
    }
}
