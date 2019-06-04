package com.example.common.common.vo;

import com.example.common.service.repository.UserRepository;
import com.example.common.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void a_유저_데이터_추가(){
        userRepository.save(User.builder().name("mins").email("mins@gmail.com").build());
    }
}