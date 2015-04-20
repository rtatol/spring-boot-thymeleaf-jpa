package com.example.boot.thymeleaf.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class DaoImpl implements Dao {

    private static final String QUERY_SELECT_ALL = "SELECT o FROM %s o ORDER BY o.id";

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public <T> void persist(final T object) {
        entityManager.persist(object);
    }

    @Override
    public <T, ID> T getById(final Class<T> clazz, final ID id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public <T> List getAll(final Class<T> clazz) {
        return entityManager.createQuery(String.format(QUERY_SELECT_ALL, clazz.getSimpleName())).getResultList();
    }

    @Override
    public <T> T merge(final T object) {
        return entityManager.merge(object);
    }

    @Override
    public <T> void remove(final T object) {
        entityManager.remove(entityManager.contains(object) ? object : entityManager.merge(object));
    }

    @Override
    public <T> List<T> findWithNamedQuery(final Class<T> clazz, final String namedQuery) {
        final Query query = createNamedQuery(clazz, namedQuery);
        return query.getResultList();
    }

    @Override
    public <T> List<T> findWithNamedQuery(final Class<T> clazz, final String namedQuery, final QueryParams queryParams) {
        final Query query = createNamedQuery(clazz, namedQuery, queryParams);
        return query.getResultList();
    }

    @Override
    public <T> T findSingleResultWithNamedQuery(final Class<T> clazz, final String namedQuery, final QueryParams queryParams) {
        final Query query = createNamedQuery(clazz, namedQuery, queryParams);
        return (T) query.getSingleResult();
    }

    @Override
    public Query createNamedQuery(final Class clazz, final String namedQuery) {
        return entityManager.createNamedQuery(namedQuery, clazz);
    }

    @Override
    public Query createNamedQuery(final Class clazz, String namedQuery, final QueryParams queryParams) {
        final Query query = entityManager.createNamedQuery(namedQuery, clazz);

        queryParams.parameters().entrySet().forEach((param) -> query.setParameter(param.getKey(), param.getValue()));

        if (queryParams.getFirstResult().isPresent()) {
            query.setFirstResult(queryParams.getFirstResult().get());
        }

        if (queryParams.getMaxResults().isPresent()) {
            query.setMaxResults(queryParams.getMaxResults().get());
        }

        return query;
    }

    @Override
    public EntityManager em() {
        return entityManager;
    }
}
