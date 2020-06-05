package com.ftt.forum.mapper;


import com.ftt.forum.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {

    @Insert("insert into comment (pid, uid, create_date, content values (#{pid}, #{uid}, #{create_date}, #{content})")
    void insert(Comment comment);

    @Delete("delete from comment where pid = #{pid}")
    void delete(Integer pid);

    @Select("select * from comment where pid = #{pid} ")
    List<Comment> select(Integer pid);
}
