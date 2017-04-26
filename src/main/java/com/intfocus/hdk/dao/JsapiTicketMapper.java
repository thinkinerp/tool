package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.JsapiTicket;

public interface JsapiTicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JsapiTicket record);

    int insertSelective(JsapiTicket record);

    JsapiTicket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JsapiTicket record);

    int updateByPrimaryKey(JsapiTicket record);
    
    List<JsapiTicket> selectByWhere(Map<String , Object> where);
    
    int deleteAll();
}