package com.forum.server.security.configs;

import com.forum.server.security.TokenAuthenticationEntryPoint;
import com.forum.server.security.user.UserDetailsServiceImpl;
import com.forum.server.security.SimpleCORSFilter;
import com.forum.server.security.TokenAuthenticationFilter;
import com.forum.server.security.TokenAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled=true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .authorizeRequests().anyRequest().access("permitAll()").and()
                //.addFilterBefore(tokenAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class)
                .addFilterBefore(simpleCORSFilter(), ChannelProcessingFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider(userDetailsService()));
    }


    @Bean
    public AuthenticationEntryPoint tokenAuthenticationEntryPoint() {
        return new TokenAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public AuthenticationProvider tokenAuthenticationProvider(UserDetailsService userDetailsService) {
        return new TokenAuthenticationProvider(userDetailsService);
    }

    @Bean
    public Filter tokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new TokenAuthenticationFilter(authenticationManager);
    }

    @Bean
    public Filter simpleCORSFilter(){
        return new SimpleCORSFilter();
    }
}
