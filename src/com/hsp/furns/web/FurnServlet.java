package com.hsp.furns.web;



import com.hsp.furns.entity.Furn;
import com.hsp.furns.entity.Page;
import com.hsp.furns.service.FurnService;
import com.hsp.furns.service.impl.FurnServiceImpl;
import com.hsp.furns.utils.DataUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 马海鑫
 * @version 1.0
 */


@WebServlet(name = "FurnServlet", value = "/manager/furnServlet" )
public class FurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();


/*
页面回显家居
 */

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   //     List<Furn> furns = furnService.queryFurns();
//        for (Furn furn: furns) {
//            System.out.println(furn);
//        }


        //把furns集合放到request域中
   //     req.setAttribute("furns",furns);


   //     req.getRequestDispatcher("/views/manager/furn_manage.jsp").forward(req,resp);
        ServletContext servletContext = getServletContext();

        resp.sendRedirect(req.getContextPath()+"/manager/furnServlet?action=page&pageNo="+servletContext.getAttribute("pageNo"));


    }


/*
处理添加家居
 */

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        System.out.println("add方法被调用");
//
//        String name = req.getParameter("name");
//        String maker = req.getParameter("maker");
//        String price = req.getParameter("price");
//        String sales = req.getParameter("sales");
//        String stock = req.getParameter("stock");


        //有些用户可能绕过前端，如postman，或把前端页面拿来去掉js验证代码，对数据库造成异常的操作

//这里我们可以对获取到的数据进行校验，如使用正则表达式，若没有验证成功返回家居添加页面，并通过request域把错误信息带过去
        //或者直接进行数据的转化
//        try {
//            int i = Integer.parseInt(sales);
//        } catch (NumberFormatException e) {
//
//
//
//            req.setAttribute("msg","销量数据格式不对。。。");
//
//            req.getRequestDispatcher("/views/manager/furn_add.jsp").forward(req,resp);
//            return;
//        }


//        System.out.println(name+" "+maker+" "+price+" "+sales+" "+stock);


//        Furn furn = null;
//        try {
//            furn = new Furn(null, name, maker, new BigDecimal(price),new Integer(sales),new Integer(stock),"assets/images/product-image/10.jpg");
//        } catch (NumberFormatException e) {
//
//            req.setAttribute("msg","添加数据格式不对。。。");
//
//            req.getRequestDispatcher("/views/manager/furn_add.jsp").forward(req,resp);
//            return;
//        }



        //使用工具类 DataUtils(底层使用 BeanUtils) 来完成自动封装
//        Furn furn = new Furn();
//        try {
//            //将 req.getParameterMap() 数据封装到furn对象
////底层使用反射将数据封装，前提：表单提交的数据的   name 必须和  封装的javabean的属性名一致
//
//            BeanUtils.populate(furn,req.getParameterMap());
//        } catch (Exception e) {
//            req.setAttribute("msg","添加数据格式不对。。。");
//
//            req.getRequestDispatcher("/views/manager/furn_add.jsp").forward(req,resp);
//            return;
//        }


        Furn furn = null;
        try {
            furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
        } catch (Exception e) {
            req.setAttribute("msg","添加数据格式不对。。。");

            req.getRequestDispatcher("/views/manager/furn_add.jsp").forward(req,resp);
            return;
        }


        int add = furnService.add(furn);

       //add 返回1表示成功，返回0表示失败
if(add  ==  1){

    resp.sendRedirect(req.getContextPath()+"/manager/furnServlet?action=list");


}else {
    req.getRequestDispatcher("/views/manager/furn_add.jsp").forward(req,resp);
}

    }


/*
通过id删除家居
 */


    protected void deleteFurnById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String id = req.getParameter("id");
        System.out.println("删除家居。。。。id为="+id);


        int i = furnService.deleteFurnById(new Integer(id));
if(i == 1){

    System.out.println("删除成功");

    resp.sendRedirect(req.getContextPath()+"/manager/furnServlet?action=list");
}else {

    System.out.println("删除失败");

}

    }


/*
通过id修改家居   showFurn回显家居    updateFurn 修改家居
 */


    protected void showFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Furn furn = furnService.queryFurnById(new Integer(id));
        req.setAttribute("furn",furn);
        req.getRequestDispatcher("/views/manager/furn_update.jsp").forward(req,resp);
    }

    protected void updateFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Furn furn = null;


        try {
           furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
            System.out.println("id="+furn.getId());
       } catch (Exception e) {
            req.setAttribute("msg","添加数据格式不对。。。");

           req.getRequestDispatcher("/views/manager/furn_update.jsp").forward(req,resp);
         return;
        }
        //修改家居，成功1，失败0
        int i = furnService.updateFurn(furn);

        if(i==1){
            System.out.println("成功修改");
            resp.sendRedirect(req.getContextPath()+"/manager/furnServlet?action=list");
        }else {
            System.out.println("修改失败");
        }

    }


    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");

        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("pageNo",pageNo);

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
//if (pageNoInteger<=0){
//    pageNoInteger =1;
//}



        Integer pageSizeInteger = null;
        if (pageSize!=null && pageSize!=""){

            try {
     pageSizeInteger = new Integer(pageSize);
            } catch (NumberFormatException e) {
      pageSizeInteger = Page.PAGE_SIZE;
            }


        }else{
     pageSizeInteger = Page.PAGE_SIZE;

        }





        Page<Furn> page = furnService.page(pageNoInteger, pageSizeInteger);




        System.out.println("page="+page);

       req.setAttribute("page",page);
       req.getRequestDispatcher("/views/manager/furn_manage.jsp").forward(req,resp);


    }




}



