package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.Survey;

public interface SurveyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Survey record);

    int insertSelective(Survey record);

    Survey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Survey record);

    int updateByPrimaryKeyWithBLOBs(Survey record);

    int updateByPrimaryKey(Survey record);

	List<Survey> selectByWhere(Map<String, String> where);

	List<Survey> selectForList(Map<String,String> where);
}