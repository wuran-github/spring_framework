package group.bridge.web.service;

import group.bridge.web.entity.Person;
import org.springframework.data.jpa.domain.Specification;

public interface PersonService extends BaseService<Person,Integer> {
    Double getAvg(Specification<Person> specification);
    Double getAvgByField(Specification<Person> specification,String field);
    Integer getSum(Specification<Person> specification);
}
