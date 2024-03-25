package com.teamproject.megabox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //시큐리티 필터 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //페이지 권한 설정
        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/member/**", "/item/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/js/**","/img/**","/css/**").permitAll() //정적필드허용
                        .anyRequest().authenticated()
        );



        //로그인
        http.formLogin(login->login
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login/error")
        );
        //로그아웃
        http.logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));

        http.csrf(cs-> cs.disable());
        return http.build();
    }
}
