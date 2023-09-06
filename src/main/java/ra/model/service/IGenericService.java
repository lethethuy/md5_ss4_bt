package ra.model.service;

import ra.model.entity.User;

import java.util.List;

public interface IGenericService <T,E>{
    List<T> findAll();
    T findByID(E id);
    User save(T p);
    void delete(E id);
}
