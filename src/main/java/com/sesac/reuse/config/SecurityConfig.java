package com.sesac.reuse.config;

import com.sesac.reuse.config.handler.OAuthLoginFailureHandler;
import com.sesac.reuse.config.handler.OAuthLoginSuccessHandler;
import com.sesac.reuse.service.UserService;
import javax.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static org.springframework.security.config.Customizer.withDefaults;

// Spring Security 설정

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired OAuthLoginSuccessHandler oAuthLoginSuccessHandler;
    @Autowired OAuthLoginFailureHandler oAuthLoginFailureHandler;
    @Autowired UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                // 로그인 페이지는 누구나 접근 가능하게.
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // oauth 로그인 설정
                .oauth2Login()
                // loginPage가 없으면 Spring Security가 제공하는 기본 OAuth 로그인 페이지가 나옴.
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(userService)
                .and()
                // 성공, 실패 핸들러 등록
                .successHandler(oAuthLoginSuccessHandler)
                .failureHandler(oAuthLoginFailureHandler);

    }
}



//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable().cors().disable()
//                .authorizeHttpRequests(request -> request
//                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        .requestMatchers("/index", "/user/login", "/user/join").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(login -> login
//                        .loginPage("/user/login")    // [A] 커스텀 로그인 페이지 지정
//                        .defaultSuccessUrl("/view/dashboard", true)
//                        .permitAll()
//                )
//                .logout(withDefaults());
//
//        return http.build();
//    }
//}



//import com.sesac.reuse.service.OAuth2UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.PrintWriter;
//import java.nio.charset.StandardCharsets;
//
//// Spring Security 설정
//@Configuration
//@EnableMethodSecurity
//public class SecurityConfig {
//    private final OAuth2UserService oAuth2UserService;
//
//    public SecurityConfig(OAuth2UserService oAuth2UserService) {
//        this.oAuth2UserService = oAuth2UserService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();  // CSRF 토큰 비활성화
//        http.authorizeHttpRequests(config -> config.anyRequest().permitAll());
//        http.oauth2Login(oauth2Configurer -> oauth2Configurer
//                .loginPage("/user/login")
//                .successHandler(successHandler())
//                .userInfoEndpoint()
//                .userService(oAuth2UserService));
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return ((request, response, authentication) -> {
//            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
//
//            String id = defaultOAuth2User.getAttributes().get("id").toString();
//            String body = """
//                    {"id":"%s"}
//                    """.formatted(id);
//
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//            PrintWriter writer = response.getWriter();
//            writer.println(body);
//            writer.flush();
//        });
//    }
//}
