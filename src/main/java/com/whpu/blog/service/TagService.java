package com.whpu.blog.service;

import com.whpu.blog.pojo.Tag;

import java.util.List;

/**
 * @InterfaceName TagService
 * @Description: 标签服务层接口
 * @Author jy
 * @Date 2020/4/22
 **/
public interface TagService {
    Tag getTag(Integer id);

    List<Tag> getAllTags();

    Tag getTagByName(String name);

    List<Tag> getAllTagsByNums();

    List<Tag> getAllTagsIncludeNums();

    int saveTag(Tag tag);

    int updateTag(Tag tag);

    int deleteTag(Integer id);
}
