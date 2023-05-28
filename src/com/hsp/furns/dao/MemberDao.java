package com.hsp.furns.dao;

import com.hsp.furns.entity.Member;

public interface MemberDao {
    //通过用户名返回对应的Member
    public Member queryMemberByUsername(String username);

    //提供一个保存Member对象到数据库表member的方法,返回1表示成功，返回0表示失败
    public int saveMember(Member member);

    //会员登录功能：通过用户名和密码查询用户是否存在
    public Member queryMemberByUsernameAndPassword(String username,String password);





}
