package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.JsapiToken;

public interface JsapiTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JsapiToken record);

    int insertSelective(JsapiToken record);

    JsapiToken selectByPrimaryKey(Integer id);
    
    List<JsapiToken> selectByWhere(Map<String,String>where);
    
    int deleteAll();

    int updateByPrimaryKeySelective(JsapiToken record);

    int updateByPrimaryKey(JsapiToken record);
}