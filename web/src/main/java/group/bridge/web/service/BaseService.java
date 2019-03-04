package group.bridge.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BaseService<T,ID> {
    boolean add(T t);

    void addAll(List<T> list);
    boolean update(T t);

    void delete(T t);

    void deleteById(ID id);

    List<T> getAll();

    Page<T> getAll(Pageable pageable);

    T get(ID id);
    T get2(ID id);

//    只需要把specification作为参数传进去即可获得List.

    List<T> getAllNotCascade();

    List<T> getByPredicate(Specification<T> specification);

    Page<T> getByPredicate(Specification<T> specification, Pageable pageable);
    Long count();
}
