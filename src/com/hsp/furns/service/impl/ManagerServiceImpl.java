package com.hsp.furns.service.impl;


import com.hsp.furns.dao.ManagerDao;
import com.hsp.furns.dao.impl.ManagerDaoImpl;
import com.hsp.furns.entity.Manager;
import com.hsp.furns.service.ManagerService;

/**
 * @author 马海鑫
 * @version 1.0
 */
public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao = new ManagerDaoImpl();



    @Override
    public Manager login(Manager manager) {
      return managerDao.queryManagerByUsernameAndPassword(manager.getUsername(),manager.getPassword())  ;
    }
}
