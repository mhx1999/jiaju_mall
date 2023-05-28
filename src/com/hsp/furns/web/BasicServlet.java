package com.hsp.furns.web;

import com.hsp.furns.entity.Furn;
import com.hsp.furns.entity.Page;
import com.hsp.furns.service.FurnService;
import com.hsp.furns.service.impl.FurnServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 马海鑫
 * @version 1.0
 */

public abstract class BasicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("UTF-8");




        System.out.println("BasicServlet.....");


//获取参数action的值
        String action = req.getParameter("action");
        System.out.println("action="+action);


        //通过反射，获取当前对象的方法
            //1.this 就是请求的Servlet
        //2.declaredMethod 方法对象就是当前请求的servlet对应的“action名字”的方法，该方法对象declaredMethod是变化的，根据用户请求
        //使用模板模式+反射+动态机制   简化多个if-else

        //action的v值必须和方法名保持一致
        System.out.println("this="+this);
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            System.out.println("declaredMethod="+declaredMethod);

            //使用方法对象，进行反射调用
            declaredMethod.invoke(this,req,resp);


        } catch (Exception e) {
           e.printStackTrace();
        }








    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
