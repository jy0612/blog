package com.whpu.blog.dao;

import com.whpu.blog.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName UserDao
 * @Description: 完成数据库分类操作的接口
 * @Author jy
 * @Date 2020/4/21
 **/

@Mapper
@Repository
public interface TypeDao {

    @Select("select * from tb_type where id=#{id}")
    Type selectTypeById(Integer id);

    @Select("select * from tb_type")
    List<Type> selectAllTypes();

    @Select("select * from tb_type where name=#{name}")
    Type selectTypeByName(String name);

    List<Type> selectAllTypesByNums();

    @Insert("insert into tb_type values(default,#{name})")
    int insertType(Type type);

    @Update("update tb_type set name=#{name} where id=#{id}")
    int updateType(Type type);

    @Delete("delete from tb_type where id=#{id}")
    int deleteType(Integer id);
}
