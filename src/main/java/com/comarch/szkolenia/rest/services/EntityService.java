package com.comarch.szkolenia.rest.services;

import com.comarch.szkolenia.rest.database.hibernate.EntityDAO;
import com.comarch.szkolenia.rest.model.EntityParent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityService {

    private final EntityDAO entityDAO;

    public void persist(EntityParent entityParent) {
        this.entityDAO.persistParent(entityParent);
    }

    public EntityParent getById(Long id) {
        return this.entityDAO.getParentById(id);
    }
}
