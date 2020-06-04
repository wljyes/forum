package com.ftt.forum.mapper;

import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {

    @Insert("insert into post (id, uid, create_date, update_date, content) values (#{id}, #{uid}, #{create_date}, #{update_date}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Post post);


    @Delete("delete from post where id = #{id}")
    void delete(Integer id);

    @Update("update post set name = #{name}, password = #{password} where id = #{id}")
    void update(User user);

    @Select("select id, name, password from user where id = #{id}")
    Post selectById(Integer id);

    @Select("select * from user where name = #{name}")
    User selectByName(String name);

}
