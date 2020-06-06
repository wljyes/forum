package com.ftt.forum.mapper;


import com.ftt.forum.entity.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into collect (uid, pid, collect_date) values (#{uid}, #{pid}, #{collect_date})")
    void insert(Collect collect);

    @Delete("delete from collect where id = #{id}")
    void  delete(Integer id);

    @Select("select * from collect where id = #{id}")
    List<Collect> selectById(Integer id);
}
