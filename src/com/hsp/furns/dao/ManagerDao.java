package com.hsp.furns.dao;

import com.hsp.furns.entity.Manager;


/**
 * @author 马海鑫
 * @version 1.0
 */
public interface ManagerDao {

    //管理员登录功能：通过用户名和密码查询管理员是否存在
    public Manager queryManagerByUsernameAndPassword(String username, String password);


}
