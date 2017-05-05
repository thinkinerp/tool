package com.intfocus.hdk.dao;

import com.intfocus.hdk.vo.Survey;

public interface SurveyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Survey record);

    int insertSelective(Survey record);

    Survey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Survey record);

    int updateByPrimaryKeyWithBLOBs(Survey record);

    int updateByPrimaryKey(Survey record);
}