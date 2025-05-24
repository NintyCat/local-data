package io.github.nintycat.core;

public class InsertRepository<ID,T> extends BaseRepository<ID,T> {

    public InsertRepository(Class<T> entityClass, String file) {
        super(entityClass, file);
    }

    public T insert(T entity) {
        if (getId(entity) == null) {
            setId(entity, nextId);
            nextId++;
        }
        cache.put(getId(entity), entity);
        saveToFile();
        return entity;
    }
}
