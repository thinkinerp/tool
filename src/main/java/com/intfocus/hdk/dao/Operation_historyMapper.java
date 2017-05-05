package com.intfocus.hdk.dao;

import com.intfocus.hdk.vo.Operation_history;

public interface Operation_historyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Operation_history record);

    int insertSelective(Operation_history record);

    Operation_history selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Operation_history record);

    int updateByPrimaryKey(Operation_history record);
}