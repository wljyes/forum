package com.ftt.forum.mapper;

import com.ftt.forum.entity.Follow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowMapper {

    @Insert("insert into follow (uid, follower_id, follow_date) values (#{uid}, #{follower_id}, #{follow_date})")
    void insert(Follow follow);

    @Delete("delete from follow where id = #{id}")
    void delete(int id);

    @Select("select * from follow where uid = #{uid}")
    List<Follow> selectByUid(int uid);

    @Select("select * from follow where follower_id = #{follower_id}")
    List<Follow> selectByFollowerId(int follower_id);

    @Select("select * from follow where uid = #{uid} and follower_id = #{followId}")
    Follow selectByUidAndFollowId(int uid, int followId);

    @Delete("delete from follow where uid = #{uid} and follower_id = #{followId}")
    void deleteByUidAndFollowId(int uid, int followId);
}