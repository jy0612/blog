package com.whpu.blog.service;

import com.whpu.blog.pojo.Type;

import java.util.List;

/**
 * @InterfaceName TypeService
 * @Description: 分类服务层接口
 * @Author jy
 * @Date 2020/4/21
 **/
public interface TypeService {
    Type getType(Integer id);

    List<Type> getAllTypes();

    Type getTypeByName(String name);

    List<Type> getAllTypesByNums();

    int saveType(Type type);

    int updateType(Type type);

    int deleteType(Integer id);
}
