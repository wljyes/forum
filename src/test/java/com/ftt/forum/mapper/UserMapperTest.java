package com.ftt.forum.mapper;

import com.ftt.forum.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void insert() {
        User user = new User();
        user.setName("test");
        user.setPassword("test");
        userMapper.insert(user);
        assertNotNull(user.getId());
    }
}