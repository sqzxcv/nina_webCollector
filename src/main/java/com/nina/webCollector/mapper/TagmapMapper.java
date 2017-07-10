package com.nina.webCollector.mapper;

import com.nina.webCollector.model.Tagmap;
import com.nina.webCollector.model.TagmapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagmapMapper {
    int countByExample(TagmapExample example);

    int deleteByExample(TagmapExample example);

    int deleteByPrimaryKey(Integer tagid);

    int insert(Tagmap record);

    int insertSelective(Tagmap record);

    List<Tagmap> selectByExample(TagmapExample example);

    Tagmap selectByPrimaryKey(Integer tagid);

    int updateByExampleSelective(@Param("record") Tagmap record, @Param("example") TagmapExample example);

    int updateByExample(@Param("record") Tagmap record, @Param("example") TagmapExample example);

    int updateByPrimaryKeySelective(Tagmap record);

    int updateByPrimaryKey(Tagmap record);
}