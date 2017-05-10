package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.State;

public interface StateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(State record);

    int insertSelective(State record);

    State selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(State record);

    int updateByPrimaryKey(State record);
    
    List<State> selectByWhere(Map<String,String> where);
}