package com.ftt.forum.mapper;

import com.ftt.forum.entity.Post;
import com.ftt.forum.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {

    @Insert("insert into post (uid, create_date, update_date, content) values (#{uid}, #{create_date}, #{update_date}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Post post);


    @Delete("delete from post where id = #{id}")
    void delete(int id);

    @Update("update post set update_date = #{update_date} where id = #{id}")
    void updateDate(Post post);

    @Select("select * from post where id = #{id}")
    Post selectById(int id);

    @Select("select * from post order by update_date desc")
    List<Post> selectList();
}
