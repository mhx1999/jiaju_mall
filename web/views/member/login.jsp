<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>韩顺平教育-家居网购</title>
    <!-- 使用base标签，让页面资源使用一个固定的参考路径 -->
    <base href="<%=request.getContextPath() + "/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
   <!--引入Jquery jar包 -->
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        //页面加载完成之后执行
            $(function (){

//模拟点击
                if("${requestScope.active}" == "register"){
                    $("#register_tab")[0].click();
                }



                // 对验证码图片进行处理,绑定一个点击事件，可以获取新的验证码
                $("#codeImg").click(function (){
                    //很多浏览器在url没有变化时，不会发送新的请求，为了防止不请求，不刷新，可以携带一个变化的参数

                    this.src = "<%=request.getContextPath()%>/kaptchaServlet?d=" + new Date();

                })


          <c:if test="${!empty requestScope.errorMsg}">
                $("span[class='errorMsg']").text("验证码输入错误！");
          </c:if>







            //正则表达式必须字母，数字下划线组成，并且长度为 6 到 10 位
            var patt = /^\w{6,10}$/;
            //邮箱的正则表达式
            var patt2 = /^[\w-]+@([a-zA-Z]+\.)+[a-zA-Z]+$/;

            //给会员注册按钮绑定单击事件
            $("#sub-btn").click(function (){



            //获取username对象
            var $username = $("#username");
            //获取password对象
            var $password = $("#password");
            //获取repwd对象
            var $repwd = $("#repwd");
            //获取email对象
            var $email = $("#email");

                //验证码不能为空
              var codeText = $("#code").val();


                //获取username输入框中的内容
                   var usernameText = $username.val();
             //获取password输入框中的内容
              var passwordText = $password.val();
            //获取repwd输入框中的内容
                 var repwdText = $repwd.val();
            //获取email输入框中的内容
                 var emailText = $email.val();


      //验证username输入框中的内容是否满足要求
    if(!patt.test(usernameText)){

        $("span[class='errorMsg']").text("用户名格式不正确，必须字母，数字下划线组成，并且长度为 6 到 10 位");
        return false;
    }

    if(!patt.test(passwordText)){

                //验证password输入框中的内容是否满足要求

        $("span[class='errorMsg']").text("密码格式不正确，必须字母，数字下划线组成，并且长度为 6 到 10 位");
        return false;

    }

    if(passwordText != repwdText){

                //验证确认密码和密码是否相等

                    $("span[class='errorMsg']").text("确认密码和密码不同，输入错误");
                    return false;

                }


    if(!patt2.test(emailText)){
        // 验证邮箱格式是否正确
        $("span[class='errorMsg']").text("邮箱格式错误！");
        return false;
    }
           //     $("span[class='errorMsg']").text("验证通过！");



//去掉验证码前后空格
                codeText=$.trim(codeText);
                if(codeText == null || codeText == ""){
                    //提示
                    $("span[class='errorMsg']").text("验证码不能为空！");
                 //   $("span.errorMsg").text("验证码不能为空");
                    return false;
                }




                return true;
})
})





    </script>
</head>

<body>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top Message Start -->
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

            </div>
        </div>
    </div>
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo" /></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Main Menu Start -->
    <div style="width: 100%;height: 50px;background-color: black"></div>
    <!-- Main Menu End -->
</div>
<!-- Header Area End  -->
<!-- login area start -->
<div class="login-register-area pt-70px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                <div class="login-register-wrapper">
                    <div class="login-register-tab-list nav">
                        <a class="active" data-bs-toggle="tab" href="#lg1">
                            <h4>会员登录</h4>
                        </a>
                        <a id="register_tab" data-bs-toggle="tab" href="#lg2">
                            <h4>会员注册</h4>
                        </a>
                    </div>
                    <div class="tab-content">
                        <div id="lg1" class="tab-pane active">
                            <div class="login-form-container">
                                <div class="login-register-form">
<%--                                    提示错误信息--%>
                                    <span style="font-size: 18pt;font-weight: bold;float: right;color: gainsboro" >${requestScope.msg}</span>

                                    <form action="memberServlet" method="post">
                                        <input type="hidden" name="action" value="login"/>
                                        <input type="text" name="user-name" value="${requestScope.username}" placeholder="Username"/>
                                        <input type="password" name="user-password" placeholder="Password"/>
                                        <div class="button-box">
                                            <div class="login-toggle-btn">
                                                <input type="checkbox"/>
                                                <a class="flote-none" href="javascript:void(0)">Remember me</a>
                                                <a href="#">Forgot Password?</a>
                                            </div>
                                            <button type="submit"><span>Login</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div id="lg2" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <span class="errorMsg"
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;"></span>
                                    <!-- 注册表单 -->
                                    <form action="/jiaju_mall/memberServlet?action=register" method="post">
                                        <input type="hidden" name="action" value="register"/>
                                        <input type="text" id="username" name="username"  placeholder="Username" value="${requestScope.username}"/>
                                        <input type="password" id="password" name="password" placeholder="输入密码"/>
                                        <input type="password" id="repwd" name="repassword" placeholder="确认密码"/>
                                        <input name="email" id="email" placeholder="电子邮件" type="email" value="${requestScope.email}"/>
                                        <input type="text" id="code" name="code" style="width: 50%"
                                               placeholder="验证码"/>　　<img id="codeImg" alt="" src="kaptchaServlet" style="width:120px;height:50px;">
                                        <div class="button-box">
                                            <button type="submit" id="sub-btn"><span>会员注册</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login area end -->

<!-- Footer Area Start -->
<div class="footer-area">
    <div class="footer-container">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px" data-aos="fade-up"
                         data-aos-delay="400">
                        <div class="single-wedge">
                            <h4 class="footer-herading">信息</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="about.html">关于我们</a></li>
                                        <li class="li"><a class="single-link" href="#">交货信息</a></li>
                                        <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a></li>
                                        <li class="li"><a class="single-link" href="#">条款和条件</a></li>
                                        <li class="li"><a class="single-link" href="#">制造</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-lm-30px" data-aos="fade-up" data-aos-delay="600">
                        <div class="single-wedge">
                            <h4 class="footer-herading">我的账号</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="my-account.html">我的账号</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="cart.html">我的购物车</a></li>
                                        <li class="li"><a class="single-link" href="login.jsp">登录</a></li>
                                        <li class="li"><a class="single-link" href="wishlist.html">感兴趣的</a></li>
                                        <li class="li"><a class="single-link" href="checkout.html">结账</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="800">

                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="row flex-sm-row-reverse">
                    <div class="col-md-6 text-right">
                        <div class="payment-link">
                            <img src="#" alt="">
                        </div>
                    </div>
                    <div class="col-md-6 text-left">
                        <p class="copy-text">Copyright &copy; 2021 韩顺平教育~</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Area End -->
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>