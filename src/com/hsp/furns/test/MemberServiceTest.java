package com.hsp.furns.test;

import com.hsp.furns.entity.Member;
import com.hsp.furns.service.MemberService;
import com.hsp.furns.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import sun.management.snmp.jvmmib.JVM_MANAGEMENT_MIBOidTable;

public class MemberServiceTest {
   private MemberService memberService = new MemberServiceImpl();


       @Test
       public void isExistsUsername(){
           if(memberService.isExistsUsername("adminx")){

               System.out.println("用户名存在");

           }else{
               System.out.println("用户名不存在");

           }
       }


       @Test
    public void registerMember(){
           Member member = new Member(null, "mhx123342", "1234567", "1282488032@qq.com");
           if(memberService.registerMember(member)){

               System.out.println("用户添加成功");

           }else{
               System.out.println("用户名添加失败");

           }


       }


    @Test
    public void login(){
        Member member = new Member(null, "mhx1999", "123456", null);
        System.out.println(memberService.login(member));



    }

}
