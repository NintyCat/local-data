package com.sixty.core;

import java.util.ArrayList;
import java.util.List;

public class QueryRepository<ID,T> extends BaseRepository<ID,T> {

    protected final List<Condition<T>> conditions = new ArrayList<>();

    public QueryRepository(Class<T> entityClass, String file) {
        super(entityClass, file);
    }

    protected List<T> findByComplexConditions(List<Condition<T>> conditions) {
        List<T> result = new ArrayList<>();
        for (T entity : cache.values()) {
            boolean match = true;
            for (Condition<T> condition : conditions) {
                if (!condition.test(entity)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                result.add(entity);
            }
        }
        return result;
    }

    @FunctionalInterface
    public interface Condition<T> {
        boolean test(T entity);
    }

    public QueryRepository<ID, T> condition(Condition<T> condition) {
        this.conditions.add(condition);
        return this;
    }

    public List<T> execute() {
        return findByComplexConditions(this.conditions);
    }
}
