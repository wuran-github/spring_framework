package group.bridge.web.serviceImpl;


import group.bridge.web.dao.PersonRepository;
import group.bridge.web.entity.Person;
import group.bridge.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;

@Service
public class PersonServiceImpl extends BaseServiceImpl<Person,Integer> implements PersonService {

    @Autowired
    PersonRepository personRepository;
    @Override
    protected void setRepository() {
        this.repository=personRepository;
    }

    @Override
    public Double getAvg(Specification<Person> specification) {
        //通过entityManager获取CriteriaBuilder、CriteriaQuery和Root
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> c = cb.createQuery(Double.class);
        Root<Person> root=c.from(Person.class);
        Predicate p=specification.toPredicate(root,c,cb);

        //condition
        Path<Integer> age=root.get("age");

        //聚合查询
//        Predicate p=cb.gt(age,18);
        c.where(p);
        c.select(cb.avg(age));
        Double result=entityManager.createQuery(c).getSingleResult();
        return result;


    }

    @Override
    public Double getAvgByField(Specification<Person> specification, String field) {
        //通过entityManager获取CriteriaBuilder、CriteriaQuery和Root
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> c = cb.createQuery(Double.class);
        Root<Person> root=c.from(Person.class);
        Predicate p=specification.toPredicate(root,c,cb);

        //condition
        Path avgField=root.get(field);

        //聚合查询
//        Predicate p=cb.gt(age,18);
        c.where(p);
        c.select(cb.avg(avgField));

        Double result=entityManager.createQuery(c).getSingleResult();
        return result;
    }

    @Override
    public Integer getSum(Specification<Person> specification) {
        //通过entityManager获取CriteriaBuilder、CriteriaQuery和Root
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> c = cb.createQuery(Integer.class);
        Root<Person> root=c.from(Person.class);

        Predicate p=specification.toPredicate(root,c,cb);
        //condition
        Path<Integer> age=root.get("age");

        //聚合查询
//        Predicate p=cb.gt(age,18);
        c.where(p);
        c.select(cb.sum(age));

        Integer result=entityManager.createQuery(c).getSingleResult();
        return result;
    }
}
