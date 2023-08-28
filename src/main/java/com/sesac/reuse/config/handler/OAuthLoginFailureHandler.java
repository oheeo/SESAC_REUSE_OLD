package com.sesac.reuse.config.handler;

import com.sesac.reuse.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;


// 로그인 실패 핸들러
@Slf4j
@Component
public class OAuthLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        log.error("LOGIN FAILED : {}", exception.getMessage());

        super.onAuthenticationFailure(request, response, exception);
    }


}
