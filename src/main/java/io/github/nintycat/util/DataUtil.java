package io.github.nintycat.util;

import io.github.nintycat.annotation.JsonEntity;
import io.github.nintycat.core.DeleteRepository;
import io.github.nintycat.core.InsertRepository;
import io.github.nintycat.core.QueryRepository;

public class DataUtil {

    private static String getJsonEntityPath(Class<?> entityClass) {
        JsonEntity jsonEntity = entityClass.getAnnotation(JsonEntity.class);
        if (jsonEntity != null) {
            return jsonEntity.file();
        } else {
            throw new RuntimeException("Entity class must be annotated with @JsonEntity");
        }
    }

    public static <ID, T> InsertRepository<ID, T> save(Class<T> entityClass) {
        String file = getJsonEntityPath(entityClass);
        return new InsertRepository<>(entityClass, file);
    }

    public static <ID, T> QueryRepository<ID, T> find(Class<T> entityClass) {
        String file = getJsonEntityPath(entityClass);
        return new QueryRepository<>(entityClass, file);
    }

    public static <ID, T> DeleteRepository<ID, T> del(Class<T> entityClass) {
        String file = getJsonEntityPath(entityClass);
        return new DeleteRepository<>(entityClass, file);
    }
}