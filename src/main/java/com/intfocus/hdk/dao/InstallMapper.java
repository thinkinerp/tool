package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.Install;

public interface InstallMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Install record);

    int insertSelective(Install record);

    Install selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Install record);

    int updateByPrimaryKeyWithBLOBs(Install record);

    int updateByPrimaryKey(Install record);

	List<Install> selectByWhere(Map<String, String> where);
}