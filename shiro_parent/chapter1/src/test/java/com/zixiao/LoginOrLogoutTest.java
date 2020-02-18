package com.zixiao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class LoginOrLogoutTest {

    public LoginOrLogoutTest()
    {
        //log= LogFactory.getLog(LoginOrLogoutTest.class);
    }

    //private Log log =null;

    @Test
    public void testHelloWorld(){
       //1、获取SecurityManager工厂，此处使用Ini配置文件初始化
        Factory<org.apache.shiro.mgt.SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");

        //2、得到securityManager,并设置给SecurityUtils
        SecurityManager manager=factory.getInstance();
        SecurityUtils.setSecurityManager(manager);

        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
        try{
            subject.login(token);
        }
        catch (AuthenticationException e)
        {
            e.printStackTrace();
            //log.info("start....");
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
    }
}
