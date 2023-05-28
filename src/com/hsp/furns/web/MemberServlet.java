package com.hsp.furns.web;

import com.hsp.furns.entity.Member;
import com.hsp.furns.service.MemberService;
import com.hsp.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author 马海鑫
 * @version 1.0
 */
@WebServlet(name = "MemberServlet", value = "/memberServlet")
public class MemberServlet extends BasicServlet {


    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//        String action = req.getParameter("action");
//        if (action.equals("login")) {
//
//            login(req,resp);
//        } else if (action.equals("register")) {
//
//            register(req,resp);
//
//        }else {
//
//            resp.getWriter().write("请求参数action有误");
//        }
//
//
//    }


    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login被调用。。。");
        String username = req.getParameter("user-name");
        String password = req.getParameter("user-password");
        Member member = memberService.login(new Member(null, username, password, null));



        if (member == null) {


            //把登录错误信息放入request区中
            req.setAttribute("msg", "用户名或者密码错误");
            req.setAttribute("username", username);

            req.getRequestDispatcher("/views/member/login.jsp")
                    .forward(req, resp);

        } else {
//登录成功
            req.setAttribute("loginUsername",username);
            req.getRequestDispatcher("/views/member/login_ok.jsp")
                    .forward(req, resp);
        }


    }




    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        System.out.println("register被调用。。。");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");


        //获取用户输入的验证码
        String code = req.getParameter("code");
        //获取谷歌生成的的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println(token);
//立即删除session中的验证码,防止该验证码被重复使用
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        //比较用户输入的验证码和谷歌生成的验证码是否相同
        if(token!=null && token.equalsIgnoreCase(code)){
            //验证码正确


            //判断这个用户名是不是可用的
            if (!memberService.isExistsUsername(username)) {
                //注册
                Member member = new Member(null, username, password, email);
                if (memberService.registerMember(member)) {
                    req.getRequestDispatcher("/views/member/register_ok.html")
                            .forward(req, resp);
                    System.out.println("注册成功");
                } else {
                    System.out.println("注册失败");
                    req.getRequestDispatcher("/views/member/register_fail.html")
                            .forward(req, resp);
                }
            } else {

                //返回注册页面
                //后面可以加入提示信息
                req.getRequestDispatcher("/views/member/login.jsp")
                        .forward(req, resp);

            }



        }else{
            //验证码错误
           //传入错误信息
            req.setAttribute("errorMsg","验证码错误！");

            //回显名字和电子邮件
            req.setAttribute("username",username);
            req.setAttribute("email",email);


            //返回注册页面
req.setAttribute("active","register");

            req.getRequestDispatcher("/views/member/login.jsp")
                    .forward(req, resp);

        }





    }



    //loginout  退出登录


    protected void loginout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//<%session.setAttribute("loginUsername",request.getAttribute("loginUsername"));%>
//
//<%session.setAttribute("isLogin",1);%>



        System.out.println("=======================================================");
        Object username = req.getSession().getAttribute("loginUsername");
        System.out.println(username);
        Object isLogin = req.getSession().getAttribute("isLogin");
        System.out.println(isLogin);
        System.out.println("=======================================================");
        req.getSession().removeAttribute("loginUsername");
        req.getSession().removeAttribute("isLogin");
        Object username2 = req.getSession().getAttribute("loginUsername");
        Object isLogin2 = req.getSession().getAttribute("isLogin");

        System.out.println(username2);
        System.out.println(isLogin2);
        System.out.println();
        resp.sendRedirect("/jiaju_mall/index.jsp");
    }
}