package com.hsp.furns.web;

import com.hsp.furns.entity.Furn;
import com.hsp.furns.entity.Page;
import com.hsp.furns.service.FurnService;
import com.hsp.furns.service.impl.FurnServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 马海鑫
 * @version 1.0
 */




@WebServlet(name = "CustomerFurnServlet", value = "/customerFurnServlet" )
public class CustomerFurnServlet  extends BasicServlet{


    private FurnService furnService = new FurnServiceImpl();

 /*
 这里仍是分页请求家居信息的api
  */

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("navigationPage",20);


        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");

        Integer pageNoInteger = null;

        if (pageNo!=null && pageNo!=""){

            try {
                pageNoInteger = new Integer(pageNo);
            } catch (NumberFormatException e) {
                pageNoInteger = 1;
            }


        }else{
            pageNoInteger = 1;

        }


        Integer pageSizeInteger = null;
        if (pageSize!=null && pageSize!=""){

            try {
                pageSizeInteger = new Integer(pageSize);
            } catch (NumberFormatException e) {
                pageSizeInteger = 4;
            }


        }else{
            pageSizeInteger = 4;

        }





        Page<Furn> page = furnService.page(pageNoInteger, pageSizeInteger);



        req.setAttribute("page",page);
        req.getRequestDispatcher("/views/customer/index.jsp").forward(req,resp);



    }


 /*
通过模糊的家居名返回page对象
  */


    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //区别导航条
        req.setAttribute("navigationPage",0);

        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");





        String name = req.getParameter("search");
       if(null == name){

           name = "";
       }



        System.out.println(name);



        Integer pageNoInteger = null;

        if (pageNo!=null && pageNo!=""){

            try {
                pageNoInteger = new Integer(pageNo);
            } catch (NumberFormatException e) {
                pageNoInteger = 1;
            }


        }else{
            pageNoInteger = 1;

        }


        Integer pageSizeInteger = null;
        if (pageSize!=null && pageSize!=""){

            try {
                pageSizeInteger = new Integer(pageSize);
            } catch (NumberFormatException e) {
                pageSizeInteger = 4;
            }


        }else{
            pageSizeInteger = 4;

        }


        Page<Furn> page = furnService.pageByName(name, pageNoInteger, pageSizeInteger);


        req.setAttribute("page",page);
        req.setAttribute("search",name);
        req.getRequestDispatcher("/views/customer/index.jsp").forward(req,resp);
    }
}
