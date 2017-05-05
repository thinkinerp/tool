package com.intfocus.hdk.dao;

import com.intfocus.hdk.vo.Shops;

public interface ShopsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shops record);

    int insertSelective(Shops record);

    Shops selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shops record);

    int updateByPrimaryKey(Shops record);
}