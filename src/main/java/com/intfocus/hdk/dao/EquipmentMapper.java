package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.Equipment;

public interface EquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
    
    List<Equipment> selectByWhere(Map<String,String>where	);
}