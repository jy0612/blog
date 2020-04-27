package com.whpu.blog.service.impl;

import com.whpu.blog.dao.TypeDao;
import com.whpu.blog.pojo.Type;
import com.whpu.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TypeServiceImpl
 * @Description: 分类服务层实现类
 * @Author jy
 * @Date 2020/4/21
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public Type getType(Integer id) {
        return typeDao.selectTypeById(id);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeDao.selectAllTypes();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.selectTypeByName(name);
    }

    @Override
    public List<Type> getAllTypesByNums() {
        return typeDao.selectAllTypesByNums();
    }

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.insertType(type);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Integer id) {
        return typeDao.deleteType(id);
    }

}
