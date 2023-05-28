package com.hsp.furns.test;

import com.hsp.furns.dao.FurnDao;
import com.hsp.furns.dao.ManagerDao;
import com.hsp.furns.dao.MemberDao;
import com.hsp.furns.dao.impl.FurnDaoImpl;
import com.hsp.furns.dao.impl.ManagerDaoImpl;
import com.hsp.furns.dao.impl.MemberDaoImpl;
import com.hsp.furns.entity.Furn;
import com.hsp.furns.entity.Manager;
import com.hsp.furns.entity.Member;
import com.hsp.furns.entity.Page;
import com.hsp.furns.service.FurnService;
import com.hsp.furns.service.impl.FurnServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class MemberDAOTest {

    private FurnDao furnDao = new FurnDaoImpl();
   private MemberDao memberDao = new MemberDaoImpl();
    private ManagerDao managerDao = new ManagerDaoImpl();


    private FurnService furnService = new FurnServiceImpl();


   @Test
    public void queryMemberByUsername(){
       Member member = memberDao.queryMemberByUsername("adminx");
       if(member == null){
           System.out.println("该用户不存在");
       }else{
           System.out.println(member.getEmail());
       }

   }


   @Test
   public void saveMember(){
       Member member = new Member(null, "mhx123", "1234567", "1282488032@qq.com");
       int i = memberDao.saveMember(member);
       System.out.println(i);

   }

    @Test
    public void queryMember(){
        Member member = memberDao.queryMemberByUsernameAndPassword("mhx1999","123456");
        if(member == null){
            System.out.println("该用户不存在");
        }else{
            System.out.println(member.getEmail());
        }

    }


    @Test
    public void queryManager(){
        Manager manager = managerDao.queryManagerByUsernameAndPassword("admin","admin");
        if(manager == null){
            System.out.println("该管理员不存在");
        }else{
            System.out.println(manager.getUsername());
        }

    }



//NULL , ' 温 馨 风 格 盆 景 架 ' , ' 蚂 蚁 家 居 ' , 180 , 666 , 7 ,'assets/images/product-image/16.jpg'
//
//    private Integer id;   //防止null
//    private String name;
//    private String maker;
//    private BigDecimal price;
//    private Integer sales;
//    private Integer stock;
//    //表的字段是img_path，这里使用的是imgPath
//    private String imgPath;


    @Test
    public void addFun(){

        Furn furn = new Furn(null, "马海鑫创建的家居", "machine家居", new BigDecimal(999),999,999,"assets/images/product-image/10.jpg");
        int i = furnDao.addFurn(furn);
        System.out.println(i);

    }
@Test
    public void getPageItems(){

        List<Furn> pageItems = furnDao.getPageItems(0,3);
        for(Furn furn:pageItems){

            System.out.println(furn);
        }
    }



    @Test
    public void page(){
        Page<Furn> page = furnService.page(1,3);
        System.out.println("page="+page);
    }

    @Test
    public void test(){
        int i = furnDao.getPageTotalRowByName("风");

        System.out.println(i);
    }


    @Test
    public void getPageItemsByName(){

        List<Furn> pageItemsByName = furnDao.getPageItemsByName("风",2,2);
        System.out.println(pageItemsByName);
    }
}
