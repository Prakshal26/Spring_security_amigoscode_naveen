package com.spring_security_navenn.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

/*
To configure our own password we can have 2 ways:
1. Create Password manually here.
2. Fetch it from database.

To work with this we need to have below two annotations and also have to extend WebSecurityConfigurerAdapter
class.
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {



// // Below is the steps to configure password manually.

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//
//      //Creating the list of users and store as many as we want.
//        List<UserDetails> users = new ArrayList<>();
        //in that list adding the user with name naveen and password 1234.
//        users.add(User.withDefaultPasswordEncoder().username("Naveen").password("1234").roles("USER").build());
//
        /*
        Telling it to store that username and pass in memory as soon as app is running.
        It will not store in some other memory, spring security has its own inMemory kind of memory where
        it maintains all the users .
        */
//        return new InMemoryUserDetailsManager(users);
//
//
//    }

    //2 way: Fetch the details from the database.
    /*
    Step 1: We have created a class called User, which is basically a table of user and will
    create a JPA. Here we are not storing anything in that table. We have created a table
    called user in user database previously using mysql client and stored some data in that table which has
    username and password.

    Step 2: To connect to the database we have specified the steps in application.properties. There it will
    connect to the mysql connector and database and will connect to database user.

    Step 3: This is the file which will run i.e AppSecurityConfig.
            Here now we are specifying UserDetailsService, which is a inbuild Interface provided in
            spring security.
            As this is a interface so we have created a implementation of this interface as MyUserDetailsService.
            So that wil be called.

     Step 4: That class he calling the repository which has the User data JPA stored in it. So it will
            search a user based on username in JPA repository. Read further in MyUserDetailService.

     Step 5: It will send the user to us based on userName and will allow us to login.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);

        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        //By default password will not be Encrypted. We need to encrypt it then use Bcrypt.
        //provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;
    }

    /*
    Now if we do not want to use the login form provided by spring security then we can
    create a login form for us.
    This functions has several configuration options on which we can work. Such as specify our own
    custom login page.
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                //This specify that we do not want authentication on login and welcome page. I.e user will be able to see login and welcome page even without logging into DB.
                .authorizeRequests().antMatchers("/login","/welcome").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll() //It will come when request will be 8080:login
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//In this feild spring looks when it should logout the user. So we are telling him to logout when it see /logout matching.
                .logoutSuccessUrl("/logout-success").permitAll();//what URL to call after user has logged out.

    }
}
