package com.whpu.blog.service;

import com.whpu.blog.pojo.User;

/**
 * @InterfaceName UserService
 * @Description: 用户服务层接口
 * @Author jy
 * @Date 2020/4/21
 **/
public interface UserService {
    User checkUser(String username,String password);
}
