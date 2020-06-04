package com.ftt.forum.mapper;

import com.ftt.forum.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Insert("insert into user (name, password) values (#{name}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);


    @Delete("delete from user where id = #{id}")
    void delete(int id);

    @Update("update user set name = #{name}, password = #{password} where id = #{id}")
    void update(User user);

    @Select("select id, name, password from user where id = #{id}")
    User selectById(int id);

    @Select("select * from user where name = #{name}")
    User selectByName(String name);
}
