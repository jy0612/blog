package com.whpu.blog.service.impl;

import com.whpu.blog.dao.TagDao;
import com.whpu.blog.pojo.Tag;
import com.whpu.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TagServiceImpl
 * @Description: 标签服务层实现类
 * @Author jy
 * @Date 2020/4/22
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public Tag getTag(Integer id) {
        return tagDao.selectTagById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.selectAllTags();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.selectTagByName(name);
    }

    @Override
    public List<Tag> getAllTagsByNums() {
        return tagDao.selectAllTagsByNums();
    }

    @Override
    public List<Tag> getAllTagsIncludeNums() {
        return tagDao.selectAllTagsIncludeNums();
    }

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.insertTag(tag);
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Transactional
    @Override
    public int deleteTag(Integer id) {
        return tagDao.deleteTag(id);
    }
}
