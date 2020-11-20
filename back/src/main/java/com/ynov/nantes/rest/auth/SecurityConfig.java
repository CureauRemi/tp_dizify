package com.ynov.nantes.rest.auth;

import com.ynov.nantes.rest.service.ServiceFindUserEmail;
import com.ynov.nantes.rest.service.ServiceFindUserEmailAuthenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final ServiceFindUserEmailAuthenticate serviceFindUserEmailAuthenticate;
    /*@Autowired
    private JwtRequestFilter jwtRequestFilter;*/
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(ServiceFindUserEmailAuthenticate serviceFindUserEmailAuthenticate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.serviceFindUserEmailAuthenticate = serviceFindUserEmailAuthenticate;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(serviceFindUserEmailAuthenticate).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/sign-in", "/sign-up", "/swagger-ui.html", "/v2/api-docs")
                .permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.csrf().disable().cors();

    }

}
