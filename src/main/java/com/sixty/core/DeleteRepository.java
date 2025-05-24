package com.sixty.core;

public class DeleteRepository<ID,T> extends BaseRepository<ID,T>{
    public DeleteRepository(Class<T> entityClass, String file) {
        super(entityClass, file);
    }

    public boolean delete(ID id) {
        T entity = findById(id);
        if (entity != null) {
            cache.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }
}
