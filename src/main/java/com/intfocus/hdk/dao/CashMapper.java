package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.Cash;

public interface CashMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cash record);

    int insertSelective(Cash record);

    Cash selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cash record);

    int updateByPrimaryKey(Cash record);

	List<Cash> selectByWhere(Map<String, String> where);
}