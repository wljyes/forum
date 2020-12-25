package com.ftt.forum.mapper;


import com.ftt.forum.entity.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Repository
public interface CollectMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into collect (uid, pid, collect_date) values (#{uid}, #{pid}, #{collect_date})")
    void insert(Collect collect);

    @Delete("delete from collect where id = #{id}")
    void  delete(Integer id);

    @Select("select * from collect where id = #{id}")
    Collect selectById(Integer id);

    @Select("select * from collect where uid = #{uid} order by collect_date desc")
    List<Collect> selectByUid(int uid);

    @Delete("delete from collect where uid = #{uid} and pid = #{pid}")
    void deleteByUidAndPid(int uid, int pid);

    @Select("select * from collect where uid = #{uid} and pid = #{pid}")
    Collect selectByUidAndPid(int uid, int pid);
}
