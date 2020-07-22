package com.spring_security_navenn.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {



    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/")
    public String home() {
        System.out.println("Home Controller");
        return "home";
    }
//NOTE ******* We do not want request mapping for login and logout is we are using spring defualt login and logout page.
    //*********But here we have created our own login and logout page so we need it;s mapping.


    /*
    This two fields are quite confusing. If user has not logged in and he tries to log in then
    he will land in this login mapping and will go to login.html.
    But if user is already logged in then he will go to home mapping i.e "/".

     */
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    //If someone clicks logout from home.html then logout-success wll be called and it will land him to logout page.
    @RequestMapping("/logout-success")
    public String logoutPage() {
        return "logout";
    }

}
