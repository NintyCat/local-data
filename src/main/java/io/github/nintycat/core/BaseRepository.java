package io.github.nintycat.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nintycat.annotation.JsonId;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseRepository<ID, T> {
    protected final Class<T> entityClass;
    protected final ObjectMapper objectMapper = new ObjectMapper();
    protected final File storageFile;
    protected final Map<ID, T> cache = new ConcurrentHashMap<>();
    protected Field idField;
    protected long nextId;

    protected BaseRepository(Class<T> entityClass, String file) {
        this.entityClass = entityClass;
        this.storageFile = new File(file);
        initIdField();
        loadFromFile();
        initNextId();
    }

    protected void initIdField() {
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonId.class)) {
                field.setAccessible(true);
                this.idField = field;
                break;
            }
        }
        if (idField == null) {
            throw new RuntimeException("No field annotated with @JsonId found in " + entityClass.getName());
        }
    }

    @SuppressWarnings("unchecked")
    protected void loadFromFile() {
        if (!storageFile.exists()) return;
        try {
            T[] arr = objectMapper.readValue(storageFile, (Class<T[]>) java.lang.reflect.Array.newInstance(entityClass, 0).getClass());
            for (T entity : arr) {
                cache.put(getId(entity), entity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void initNextId() {
        nextId = cache.keySet().stream().mapToLong(id -> (Long) id).max().orElse(0L) + 1;
    }

    @SuppressWarnings("unchecked")
    protected ID getId(T entity) {
        try {
            return (ID) idField.get(entity);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected void setId(T entity, long id) {
        try {
            idField.set(entity, id);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected T findById(ID id) {
        return cache.get(id);
    }

    protected void saveToFile() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(storageFile, cache.values());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}