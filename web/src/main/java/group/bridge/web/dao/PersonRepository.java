package group.bridge.web.dao;

import group.bridge.web.entity.Person;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person,Integer> {

}
