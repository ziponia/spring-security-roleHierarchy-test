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
     * ...TODO 계층롤 추가
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        HashMap<String, List<String>> rm = new HashMap<>();

        /* 대표이사 라인 */
        rm.put(createRole("대표이사"), List.of(
                createRole("개발팀장"),
                createRole("디자인팀장"),
                createRole("회계팀장"),
                createRole("마케팅팀장")
        ));

        /* 개발팀장 라인 */
        rm.put(createRole("개발팀장"), List.of(
                createRole("프런트엔드팀"),
                createRole("백엔드팀"),
                createRole("인프라관리팀")
        ));

        String roles = RoleHierarchyUtils.roleHierarchyFromMap(rm);
        System.out.println("create role");
        System.out.println(roles);

        hierarchy.setHierarchy(roles);
        return hierarchy;
    }

    /**
     * {{@link EnableMethodSecurity}} 가 활성화 되고 {{@link PreAuthorize}} 가 선언된 메서드에 적용
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

        manager.createUser(createUser("대표이사"));
        manager.createUser(createUser("개발팀장"));
        manager.createUser(createUser("디자인팀장"));
        manager.createUser(createUser("회계팀장"));
        manager.createUser(createUser("마케팅팀장"));
        manager.createUser(createUser("프런트엔드팀"));
        manager.createUser(createUser("벡엔드팀"));
        manager.createUser(createUser("인프라관리팀"));
        manager.createUser(createUser("뉴스관리팀"));
        manager.createUser(createUser("SNS관리팀"));
        manager.createUser(createUser("안드로이드팀"));
        manager.createUser(createUser("IOS팀"));
        manager.createUser(createUser("Web팀"));
        manager.createUser(createUser("결제팀"));
        manager.createUser(createUser("주문팀"));
        manager.createUser(createUser("상품팀"));

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
