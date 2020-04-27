package com.whpu.blog.service.impl;

import com.whpu.blog.dao.UserDao;
import com.whpu.blog.pojo.User;
import com.whpu.blog.service.UserService;
import com.whpu.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description: 用户服务层实现类
 * @Author jy
 * @Date 2020/4/21
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.selectUserByUserNameAndPassword(username, MD5Utils.code(password));
    }
}
