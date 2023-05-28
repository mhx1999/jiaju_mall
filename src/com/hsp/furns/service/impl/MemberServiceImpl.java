package com.hsp.furns.service.impl;

import com.hsp.furns.dao.MemberDao;
import com.hsp.furns.dao.impl.MemberDaoImpl;
import com.hsp.furns.entity.Member;
import com.hsp.furns.service.MemberService;

public class MemberServiceImpl implements MemberService {

    //定义一个MemberDao属性


   private MemberDao memberDao = new MemberDaoImpl();



    @Override
    public boolean registerMember(Member member) {

        return   memberDao.saveMember(member) == 1 ? true : false;

    }

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return 如果存在返回true，否则返回false
     */
    @Override
    public boolean isExistsUsername(String username) {
//如看某个方法 ctrl+b :定位到memberDao 编译类型的方法
        //若使用 ctrl+alt+b  :实现类的方法
        //若有多个类实现了该方法  ，会让你选择去哪个类
        return memberDao.queryMemberByUsername(username) == null ? false : true;

    }






    @Override
    public Member login(Member member) {

        return   memberDao.queryMemberByUsernameAndPassword(
                member.getUsername(),member.getPassword()) ;

    }
}
