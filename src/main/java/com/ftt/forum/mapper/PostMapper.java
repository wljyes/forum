package com.ftt.forum.mapper;

import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {

    @Insert("insert into post (uid, create_date, content) values (#{uid}, #{create_date}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Post post);


    @Delete("delete from post where id = #{id}")
    void delete(int id);


    @Select("select * from post where id = #{id}")
    Post selectById(int id);

    @Select("select * from post order by (select create_date from comment where pid = post.id order by id desc limit 1) desc")
    List<Post> selectList();

    @Select("select * from post where uid = #{uid} order by create_date desc")
    List<Post> selectByUid(int uid);
}
