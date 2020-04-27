package com.whpu.blog.dao;

import com.whpu.blog.pojo.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName TagDao
 * @Description: 完成数据库标签操作的接口
 * @Author jy
 * @Date 2020/4/22
 **/
@Mapper
@Repository
public interface TagDao {
    @Select("select * from tb_tag where id=#{id}")
    Tag selectTagById(Integer id);

    @Select("select * from tb_tag")
    List<Tag> selectAllTags();

    @Select("select * from tb_tag where name=#{name}")
    Tag selectTagByName(String name);

    @Select("select t.id,t.name from tb_tag t,tb_blog_tags tb,tb_blog b " +
            "where t.id=tb.tag_id and tb.blog_id=b.id and b.id=#{0}")
    List<Tag> selectTagsByBlogId(Integer id);

    List<Tag> selectAllTagsByNums();

    List<Tag> selectAllTagsIncludeNums();

    @Insert("insert into tb_tag values(default,#{name})")
    int insertTag(Tag tag);

    @Update("update tb_tag set name=#{name} where id=#{id}")
    int updateTag(Tag tag);

    @Delete("delete from tb_tag where id=#{id}")
    int deleteTag(Integer id);
}
