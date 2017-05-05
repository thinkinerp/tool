package com.intfocus.hdk.dao;

import com.intfocus.hdk.vo.Printer;

public interface PrinterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Printer record);

    int insertSelective(Printer record);

    Printer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Printer record);

    int updateByPrimaryKey(Printer record);
}