package com.hsp.furns.web;


import com.hsp.furns.entity.Manager;
import com.hsp.furns.service.ManagerService;
import com.hsp.furns.service.impl.ManagerServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 马海鑫
 * @version 1.0
 */


@WebServlet(name = "ManagerServlet", value = "/managerServlet")
public class ManagerServlet extends BasicServlet {


   private ManagerService managerService = new ManagerServiceImpl();



   protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println("manager 的login被调用。。。");
      String username = req.getParameter("user-name");
      String password = req.getParameter("user-password");
      Manager manager = managerService.login(new Manager(null, username, password));



      if (manager == null) {

         //登录失败
         //把登录错误信息放入request区中
         req.setAttribute("msg", "用户名或者密码错误");
         req.setAttribute("username", username);


         //跳转回管理员登录页面


         req.getRequestDispatcher("/views/manager/manage_login.jsp")
                 .forward(req, resp);

      } else {


         // 管理员登录成功后，显示管理菜单页面


         req.getRequestDispatcher("/views/manager/manage_menu.jsp")
                 .forward(req, resp);
      }


   }



}
