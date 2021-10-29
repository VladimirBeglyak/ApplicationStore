package by.beglyakdehterenok.store.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T,ID> {

    List<T> findAll();
    void save(T t);
    Optional<T> findById(ID id);

}
