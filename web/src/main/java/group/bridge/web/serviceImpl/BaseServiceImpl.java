package group.bridge.web.serviceImpl;

import group.bridge.web.dao.BaseRepository;
import group.bridge.web.service.BaseService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseServiceImpl<T,ID> implements BaseService<T,ID>, InitializingBean {
    protected BaseRepository<T,ID> repository;
    @PersistenceContext
    EntityManager entityManager;
    protected abstract void setRepository();
    @Override
    public boolean add(T t) {

        if(repository.save(t)==null){
            return false;
        }
        return true;
    }
    @Override
    public void addAll(List<T> list) {

        repository.saveAll(list);

    }

    @Override
    public boolean update(T t) {
        if(repository.save(t)==null){
            return false;
        }
        return true;
    }


    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
    @Override
    public Page<T> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    @Override
    public List<T> getAllNotCascade() {
        return repository.findAllNotCascade();
    }
    @Override
    public T get(ID id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public T get2(ID id) {
        return repository.getOne(id);
    }
    @Override
    public List<T> getByPredicate(Specification<T> specification){
        return repository.findAll(specification);
    }
    @Override
    public Page<T> getByPredicate(Specification<T> specification,Pageable pageable){

        return repository.findAll(specification,pageable);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        setRepository();
    }
    @Override
    public Long count(){
        return repository.count();
    }
}
