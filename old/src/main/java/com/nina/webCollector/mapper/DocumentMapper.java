package com.nina.webCollector.mapper;

import com.nina.webCollector.model.Document;
import com.nina.webCollector.model.DocumentExample;
import com.nina.webCollector.model.DocumentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentMapper {
    int countByExample(DocumentExample example);

    int deleteByExample(DocumentExample example);

    int deleteByPrimaryKey(Integer docId);

    int insert(DocumentWithBLOBs record);

    int insertSelective(DocumentWithBLOBs record);

    List<DocumentWithBLOBs> selectByExampleWithBLOBs(DocumentExample example);

    List<Document> selectByExample(DocumentExample example);

    DocumentWithBLOBs selectByPrimaryKey(Integer docId);

    int updateByExampleSelective(@Param("record") DocumentWithBLOBs record, @Param("example") DocumentExample example);

    int updateByExampleWithBLOBs(@Param("record") DocumentWithBLOBs record, @Param("example") DocumentExample example);

    int updateByExample(@Param("record") Document record, @Param("example") DocumentExample example);

    int updateByPrimaryKeySelective(DocumentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DocumentWithBLOBs record);

    int updateByPrimaryKey(Document record);
}