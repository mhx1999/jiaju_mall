package com.hsp.furns.dao.impl;

import com.hsp.furns.dao.BasicDAO;
import com.hsp.furns.dao.ManagerDao;
import com.hsp.furns.entity.Manager;
import com.hsp.furns.entity.Member;


/**
 * @author 马海鑫
 * @version 1.0
 */
public class ManagerDaoImpl extends BasicDAO implements ManagerDao {


/*
      通过用户名和密码查询管理员是否存在
    传入用户名 （字符串）  和   密码   （字符串）
     */

    @Override
    public Manager queryManagerByUsernameAndPassword(String username, String password) {
        String sql = "SELECT `id`,`username`,`password` From `manager` WHERE `username` = ? AND `password` = ?";
        Object object = querySingle(sql, Manager.class, username,password);
        Manager manager = (Manager) object;
        return manager;
    }




}
