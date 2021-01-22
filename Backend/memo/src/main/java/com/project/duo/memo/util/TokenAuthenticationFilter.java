package com.project.duo.memo.util;

import com.project.duo.memo.domain.User;
import com.project.duo.memo.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtAuthenticationTokenProvider authenticationTokenProvider;

    @Autowired
    private UserService userService;


    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = authenticationTokenProvider.parseTokenString(request);
        if (authenticationTokenProvider.validateToken(token)) {
            Long userNo = authenticationTokenProvider.getTokenOwnerNo(token);
            try {
                User member = (User) userService.getUserByUsername(userNo.toString());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(member,
                        member.getUserPassword());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (UsernameNotFoundException e) {
                throw new Throwable("인증 에러");
            }
        }
        filterChain.doFilter(request, response);
    }
}