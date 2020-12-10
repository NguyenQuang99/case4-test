package com.mvc.config;

import com.mvc.service.IUserService;
import com.mvc.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


//    UserDetailsServiceImpl userService = new UserDetailsServiceImpl();

    @Autowired
    IUserService userService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Các yêu cầu phải login với vai trò ROLE_EMPLOYEE hoặc ROLE_MANAGER.
        // Nếu chưa login, nó sẽ redirect tới trang /admin/login.
        http.authorizeRequests().antMatchers("/quan-tri/trang-chu")//
                .access("hasAnyRole('ROLE_MANAGER')")
                .antMatchers("/login", "/").permitAll()
                .antMatchers("/quan-tri/trang-chu").access("hasAnyRole('ROLE_MANAGER')")
                .antMatchers("/quan-tri/bai-viet/chinh-sua").access("hasAnyRole('ROLE_MANAGER')")
                .antMatchers("/quan-tri/bai-viet/danh-sach").access("hasAnyRole('ROLE_MANAGER')");


        // Các trang chỉ dành cho MANAGER
//        http.authorizeRequests().antMatchers("/admin/product").access("hasRole('ROLE_MANAGER')");

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/accessDenied");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//

                //
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/dang-nhap")//
                .defaultSuccessUrl("/trang-chu")//
                .failureUrl("/login?error=true")//
                .usernameParameter("userName")//
                .passwordParameter("password")

                // Cấu hình cho trang Logout.
                // (Sau khi logout, chuyển tới trang home)
                .and().logout().logoutUrl("/thoat").logoutSuccessUrl("/trang-chu");

    }
}
