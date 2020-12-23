package com.ftt.forum.mapper;

import com.ftt.forum.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user (username, password) values (#{username}, #{password})")
    void insert(User user);

    @Delete("delete from user where id = #{id}")
    void delete(Integer id);

    @Update("update user set username = #{username}, password = #{password} where id = #{id}")
    void update(User user);

    @Select("select id, username, password from user where id = #{id}")
    User selectById(Integer id);

    @Select("select * from user where username = #{name}")
    User selectByName(String username);

}
