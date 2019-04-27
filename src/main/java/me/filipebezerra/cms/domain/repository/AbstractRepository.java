package me.filipebezerra.cms.domain.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractRepository<T> {

    private final List<T> elements = new ArrayList<>();

    public T findOne(String id) {
        return elements.stream().filter(item -> item.equals(id)).findFirst().get();
    }

    public List<T> findAll() {
        return elements;
    }

    public T save(T entity) {
        elements.add(entity);
        return entity;
    }

    public void delete(T entity) {
        final Iterator<T> iterator = elements.iterator();
        while (iterator.hasNext()) {
            final T next = iterator.next();
            if (next.equals(entity)) {
                iterator.remove();
                break;
            }
        }
    }

}
