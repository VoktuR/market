package ru.rav.market.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import ru.rav.market.security.dto.JwtRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.Duration.between;

@Slf4j
public class TestLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Auth request received");
        LocalDateTime startTime = LocalDateTime.now();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Sending response to user");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Auth response was sent to user");
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime statTime = (LocalDateTime) request.getAttribute("startTime");
        Duration d = between(endTime, statTime);
        log.info("Authorization time: " + d.toMinutesPart() + " ms");
    }

}
