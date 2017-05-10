package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.Printer;

public interface PrinterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Printer record);

    int insertSelective(Printer record);

    Printer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Printer record);

    int updateByPrimaryKey(Printer record);

	List<Printer> selectByWhere(Map<String, String> where);
}