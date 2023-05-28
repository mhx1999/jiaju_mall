package com.hsp.furns.dao.impl;

import com.hsp.furns.dao.BasicDAO;
import com.hsp.furns.dao.MemberDao;
import com.hsp.furns.entity.Member;

public class MemberDaoImpl extends BasicDAO implements MemberDao {
    /**
     * 通过用户名返回对应的Member
     * @param username  用户名
     * @return  对应的Member，如果没有该Member，返回null
     */

    @Override
    public Member queryMemberByUsername(String username) {

        //提示：现在sqlyog测试，然后再拿到程序中，这样可以避免很多不必要的bug
        String sql = "SELECT `id`,`username`,`password`,`email` FROM member WHERE `username` = ?";
        Object object = querySingle(sql, Member.class, username);
        Member member = (Member) object;
        return member;
    }

    /**
     * 保存一个会员
     * @param member  传入Member对象
     * @return  返回-1 表示失败；返回其他数子，表示受影响的行数
     */
    @Override
    public int saveMember(Member member) {
        String sql = "INSERT INTO `member` (`username`,`password`,`email`) VALUES(?,MD5(?),?)";
        int update = update(sql, member.getUsername(), member.getPassword(), member.getEmail());
        return update;
    }


    /*
      通过用户名和密码查询用户是否存在
    传入用户名 （字符串）  和   密码   （字符串）
     */
    @Override
    public Member queryMemberByUsernameAndPassword(String username, String password) {
        String sql = "SELECT `id`,`username`,`password`,`email` From `member` WHERE `username` = ? AND `password` = md5(?)";
        Object object = querySingle(sql, Member.class, username,password);
        Member member = (Member) object;
        return member;
    }
}
