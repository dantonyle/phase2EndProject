package com.springboot.web.model;

import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepo extends CrudRepository<UserEntity, Long> {
}
