package com.example.common.common.service.dao;

import com.example.common.common.vo.User;
import org.springframework.data.repository.CrudRepository;

/**
 * user repository
 *
 * @author minseok
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
