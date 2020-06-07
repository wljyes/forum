package com.ftt.forum.mapper;


import com.ftt.forum.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into comment (pid, uid, create_date, content) values (#{pid}, #{uid}, #{create_date}, #{content})")
    void insert(Comment comment);

    @Delete("delete from comment where id = #{id}")
    void delete(Integer id);

    @Select("select * from comment where pid = #{pid} ")
    List<Comment> select(Integer pid);

    @Select("select count(*) from comment where pid = #{pid}")
    int selectCountByPid(int pid);

    @Select("select create_date from comment where pid = #{pid} order by create_date desc limit 1")
    Date selectLastCommentDate(int pid);

}
