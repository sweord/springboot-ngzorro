package com.fj.springbootangular4;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthFailureHandler customAuthFailureHandler;

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    Gson gson = new Gson();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login").loginProcessingUrl("/auth")
                    .successHandler(customLoginSuccessHandler)
                    .failureHandler(customAuthFailureHandler)
                    .and()
                .logout().permitAll().logoutSuccessUrl("/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
        ;
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().regexMatchers(".*\\.js", ".*\\.css", ".*\\.ico", ".*\\.woff2");
    }

    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                    .withUser("user").password("password").roles("ADMIN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected class NoRedirectStrategy implements RedirectStrategy {
        @Override
        public void sendRedirect(HttpServletRequest request,
                                 HttpServletResponse response, String url) throws IOException {
            // no redirect
        }
    }

    @Component
    public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        public CustomLoginSuccessHandler() {
            super();
            setRedirectStrategy(new NoRedirectStrategy());
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication)
                throws ServletException, IOException {
            super.onAuthenticationSuccess(request, response, authentication);
            Writer writer = response.getWriter();
            writer.write(gson.toJson("success"));
        }
    }

    @Component
    public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        public CustomAuthFailureHandler() {
            super();
            setRedirectStrategy(new NoRedirectStrategy());
        }

        @Override
        public void onAuthenticationFailure(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException exception)
                throws IOException, ServletException {
            String error = exception.getMessage();
            //getRedirectStrategy().sendRedirect(request, response, "/login?error=" + error);
            Writer writer = response.getWriter();
            writer.write(gson.toJson(error));
        }
    }

    @Component
    public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Authentication authentication)
                throws IOException, ServletException {
            Writer writer = response.getWriter();
            writer.write(gson.toJson("success"));
        }
    }
}
