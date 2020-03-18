package com.fyd.cygl.controller;


import com.fyd.cygl.util.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = { "/", "/index" })
    public String index(){
        return "/log/index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map){
        System.out.println("HomeController.login()");
        //shiro获取当前subject
        Subject subject = SecurityUtils.getSubject();
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        //(AOP拦截横切认证)处理登录错误，并返回错误原因
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        //isAuthenticated():指示当前用户是否已通过身份验证(已登录)
        if(!subject.isAuthenticated()){
            System.out.println("该用户未通过身份验证，请先登录！");
            logger.error(DateUtils.getDate()+"访问失败!-->请先登录！");
            msg="先登录！";
        }
        logger.error(DateUtils.getDate()+"登录状态============>>>>>>> "+subject.isAuthenticated());
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                logger.error(DateUtils.getDate()+"账号不存在");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                logger.error(DateUtils.getDate()+"密码不正确");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                logger.error(DateUtils.getDate()+"验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                logger.error(DateUtils.getDate()+"未知错误："+exception);
                System.out.println("else -- >" + exception);
            }
        }else{
            logger.info(DateUtils.getDate()+"登录成功！");
        }

        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理，如果失败跳回login登录界面
        return "/log/login";
    }

//    @RequestMapping(value = "/regist")
//    public String regis(HttpServletRequest request, Map<String, Object> map){
//        return "/log/registered";
//    }
//    @RequestMapping(value = "/registered",produces="text/html;charset=UTF-8")
//    @ResponseBody
//    public int registered(HttpServletRequest request){
//        System.out.println("-----用户注册------");
//        //生成密码盐（部分，需要存入数据库中）
//        String sort = new SecureRandomNumberGenerator().nextBytes().toHex();
//        System.out.println("sort:"+sort);
//        User user = new User();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        user.setUsername(username);
//        user.setPassword(password);
//        System.out.println("username:"+username+"    password:"+password);
//        String result = new SimpleHash("MD5", user.getPassword(), user.getUsername() + sort, 2).toString();
//        System.out.println("1:"+sort+user.getUsername());
//        System.out.println("2"+result);
//        user.setId(2);
//        user.setPassword(result);
//        user.setSalt(sort);
//        user.setCreateTime(DateUtils.stringToDate(DateUtils.getDate()));
//        user.setRoleId(1);
//        UserService userService = new UserServiceImpl();
//        System.out.println("开始进行插入");
//        int insertResult = userService.insertUser(user);
//        System.out.println("退出插入");
//        return insertResult;
//    }



}
