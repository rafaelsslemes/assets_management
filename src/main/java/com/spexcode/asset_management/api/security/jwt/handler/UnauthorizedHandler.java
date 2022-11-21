package com.spexcode.asset_management.api.security.jwt.handler;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.spexcode.asset_management.api.security.jwt.ServletUtil;

@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {
    private static Logger logger = LoggerFactory.getLogger(UnauthorizedHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        logger.warn("UnauthorizedHandler, exception: " + authException);

        // Chamado se token errado ou ausente
        String json = ServletUtil.getJson("error", "UNAUTHORIZED");
        ServletUtil.write(response, HttpStatus.FORBIDDEN, json);
    }
}
