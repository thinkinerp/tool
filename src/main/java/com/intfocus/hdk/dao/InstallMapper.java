package com.intfocus.hdk.dao;

import com.intfocus.hdk.vo.Install;

public interface InstallMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Install record);

    int insertSelective(Install record);

    Install selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Install record);

    int updateByPrimaryKeyWithBLOBs(Install record);

    int updateByPrimaryKey(Install record);
}