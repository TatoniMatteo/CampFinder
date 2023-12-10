package com.tatonimatteo.campfinder.repository;

import com.tatonimatteo.campfinder.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
