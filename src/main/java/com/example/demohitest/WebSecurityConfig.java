package com.example.demohitest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import java.util.HashMap;
import java.util.List;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(
                        authorize -> authorize
                                .requestMatchers("/**").permitAll()
                                .expressionHandler(expressionHandler())
                )
                .formLogin();

        return http.build();
    }

    @Bean
    public SecurityExpressionHandler<FilterInvocation> expressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy());
        return handler;
    }


    /**
     * ...TODO ????????? ??????
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        HashMap<String, List<String>> rm = new HashMap<>();

        /* ???????????? ?????? */
        rm.put(createRole("????????????"), List.of(
                createRole("????????????"),
                createRole("???????????????"),
                createRole("????????????"),
                createRole("???????????????")
        ));

        /* ???????????? ?????? */
        rm.put(createRole("????????????"), List.of(
                createRole("??????????????????"),
                createRole("????????????"),
                createRole("??????????????????")
        ));

        String roles = RoleHierarchyUtils.roleHierarchyFromMap(rm);
        System.out.println("create role");
        System.out.println(roles);

        hierarchy.setHierarchy(roles);
        return hierarchy;
    }

    /**
     * {{@link EnableMethodSecurity}} ??? ????????? ?????? {{@link PreAuthorize}} ??? ????????? ???????????? ??????
     * @return
     */
    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy());
        return handler;
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(createUser("????????????"));
        manager.createUser(createUser("????????????"));
        manager.createUser(createUser("???????????????"));
        manager.createUser(createUser("????????????"));
        manager.createUser(createUser("???????????????"));
        manager.createUser(createUser("??????????????????"));
        manager.createUser(createUser("????????????"));
        manager.createUser(createUser("??????????????????"));
        manager.createUser(createUser("???????????????"));
        manager.createUser(createUser("SNS?????????"));
        manager.createUser(createUser("??????????????????"));
        manager.createUser(createUser("IOS???"));
        manager.createUser(createUser("Web???"));
        manager.createUser(createUser("?????????"));
        manager.createUser(createUser("?????????"));
        manager.createUser(createUser("?????????"));

        return manager;
    }

    private static String createRole(String name) {
        return "ROLE_" + name;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    private static UserDetails createUser(String username) {
        return User.builder()
                .username(username)
                .password(passwordEncoder().encode("1234"))
                .roles(username)
                .build();
    }
}
