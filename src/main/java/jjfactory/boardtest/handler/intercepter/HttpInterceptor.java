package jjfactory.boardtest.handler.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[REQ] ---> [METHOD] {} | [URL] {} | [qs] {} | [TOKEN] {} | reto {}",
                request.getMethod(), request.getRequestURI(), request.getQueryString(),
                request.getHeader(HttpHeaders.AUTHORIZATION),request.getHeader("Authorization: Bearer"));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[RES] ---> [STATUS] {}", response.getStatus());
    }

}
