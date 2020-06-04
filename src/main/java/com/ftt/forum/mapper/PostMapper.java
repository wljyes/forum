package com.ftt.forum.mapper;

import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into post (id, uid, create_date, update_date, content) values (#{id}, #{uid}, #{create_date}, #{update_date}, #{content})")
    void insert(Post post);

    @Delete("delete from post where id = #{id}")
    void delete(Integer id);

    @Select("select * from post where id = #{id}")
    Post selectById(Integer id);

    @Select("select * from post")
    List<Post> select();

}
