package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User>  getDepartment();
    
    List<User> selectByWhere(Map<String,String>where);
}