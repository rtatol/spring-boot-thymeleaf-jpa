package com.example.boot.thymeleaf.service;

import com.example.boot.thymeleaf.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.util.List;

public abstract class AbstractCrudService<T> {

    protected final Class<T> entityClass;

    @Autowired
    protected Dao dao;

    @SuppressWarnings("unchecked")
    public AbstractCrudService() {
        this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractCrudService.class);
    }

    public void persist(final T object) {
        dao.persist(object);
    }

    public <ID> T get(final ID id) {
        return dao.getById(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return dao.getAll(entityClass);
    }

    public T update(final T object) {
        return dao.merge(object);
    }

    public void delete(final T object) {
        dao.remove(object);
    }
}
