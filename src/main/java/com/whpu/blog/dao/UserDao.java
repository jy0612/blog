package com.whpu.blog.dao;

import com.whpu.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName UserDao
 * @Description: 完成数据库用户操作的接口
 * @Author jy
 * @Date 2020/4/21
 **/

@Mapper
@Repository
public interface UserDao {
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    User selectUserByUserNameAndPassword(@Param("username") String username,@Param("password") String password);
}
