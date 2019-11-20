package com.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.demo.entity.User;

/**
 * @author Adeel.Asghar
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
