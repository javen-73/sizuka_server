package com.javen.sizuka.modules.accountItem.mapper;

import com.javen.sizuka.model.ItemType;
import com.javen.sizuka.model.ItemTypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ItemTypeMapper {
    long countByExample(ItemTypeExample example);

    int deleteByExample(ItemTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemType record);

    int insertSelective(ItemType record);

    List<ItemType> selectByExample(ItemTypeExample example);

    ItemType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemType record, @Param("example") ItemTypeExample example);

    int updateByExample(@Param("record") ItemType record, @Param("example") ItemTypeExample example);

    int updateByPrimaryKeySelective(ItemType record);

    int updateByPrimaryKey(ItemType record);

    List<ItemType> selectAllTypes();
}