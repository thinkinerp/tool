package com.intfocus.hdk.dao;

import com.intfocus.hdk.vo.Cash;

public interface CashMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cash record);

    int insertSelective(Cash record);

    Cash selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cash record);

    int updateByPrimaryKey(Cash record);
}