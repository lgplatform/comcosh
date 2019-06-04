package com.example.common.service.repository;

import com.example.common.vo.User;
import org.springframework.data.repository.CrudRepository;

/**
 * user repository
 *
 * @author minseok
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
