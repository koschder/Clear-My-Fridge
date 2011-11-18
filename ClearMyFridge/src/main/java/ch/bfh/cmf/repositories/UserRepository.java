package ch.bfh.cmf.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.bfh.cmf.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
