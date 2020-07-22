/*

To use this project in future, pre-requite:
1. Create a database called user.
2. Create table called user inside it with field username and password.
3. Store some username and password inside it. The content you have stored will be used to login into this application.
4. Run SQL based on details mentioned in application.properties

 */
/*
Spring Security Application.
To work with spring Security we need to include below dependency.
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

This is the only dependency that we need for Spring Security. If we run this  application then by default
it will ask for username and password. The username will be 'user' and password will be some big
number that we will see when application will run.

If we want to have our own password when application will run then we have 2 ways. (Check AppSecurityConfig file for more details).

Now when we will include spring Security it will have it's own login and logout form. Everything will be
ready for us and we can just login and use the application.
But if want to customize login logout page and want to have our own login page then we can do that:
    For this create login form of your choice and put it in resources/templates.
    Then tell Spring that hey spring i do now want your login page, instead i have created my own and i want to use that.
    For that we have created onemore function in AppSecurityConfigure.

**************************************************************************************
We can use our own Login mechanism but if we want to use Suppose Google mechanism then we can
use some dependency called OAuth2 which allow us to login through google.
<!-- https://mvnrepository.com/artifact/org.springframework.security.oauth.boot/spring-security-oauth2-autoconfigure -->
<dependency>
    <groupId>org.springframework.security.oauth.boot</groupId>
    <artifactId>spring-security-oauth2-autoconfigure</artifactId>
    <version>2.3.1.RELEASE</version>
</dependency>


 */


package com.spring_security_navenn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
