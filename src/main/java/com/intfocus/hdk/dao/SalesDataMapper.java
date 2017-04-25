package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.SalesData;

public interface SalesDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalesData record);

    int insertSelective(SalesData record);

    SalesData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalesData record);

    int updateByPrimaryKey(SalesData record);

	List<SalesData> selectByWhere(Map<String , Object> where);
}