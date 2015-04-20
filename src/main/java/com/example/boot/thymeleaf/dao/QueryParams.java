package com.example.boot.thymeleaf.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// fluent query construction
// example: QueryParams.with("user.name", userName).and("user.age", age).setMaxResults(limit);
public class QueryParams {

    private static final int INITIAL_CAPACITY = 4;

    // map contains named query params {key: param name, value: param value}
    private Map<String, Object> parameters;

    // optionally limitation of result
    private Optional<Integer> firstResult;
    private Optional<Integer> maxResults;

    private QueryParams() {
        this.parameters = new HashMap<>(INITIAL_CAPACITY);
        this.firstResult = Optional.empty();
        this.maxResults = Optional.empty();
    }

    private QueryParams(final String name, final Object value) {
        this();
        this.parameters.put(name, value);
    }

    public static QueryParams with(final String name, final Object value) {
        return new QueryParams(name, value);
    }

    public QueryParams and(final String name, final Object value) {
        this.parameters.put(name, value);
        return this;
    }

    public static QueryParams empty() {
        return new QueryParams();
    }

    public Map<String, Object> parameters() {
        return this.parameters;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = Optional.of(firstResult);
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = Optional.of(maxResults);
    }

    public Optional<Integer> getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Optional<Integer> firstResult) {
        this.firstResult = firstResult;
    }

    public Optional<Integer> getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Optional<Integer> maxResults) {
        this.maxResults = maxResults;
    }
}
