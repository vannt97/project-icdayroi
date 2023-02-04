package com.vannt.projecticdayroi.jwt;

import com.google.gson.Gson;
import com.vannt.projecticdayroi.model.SubjectDataModel;
import com.vannt.projecticdayroi.payload.response.DataTokenResponse;
import com.vannt.projecticdayroi.uliti.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Autowired
    ConvertObject convertObject;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromHeader(request);
        try {
            if(token != null){
                if(jwtTokenHelper.validateToken(token)){
                    String tokenDecode = jwtTokenHelper.decodeToken(token);
                    SubjectDataModel subject = (SubjectDataModel) convertObject.convertJsonToObject(tokenDecode,SubjectDataModel.class);
                    if(StringUtils.hasText(subject.getTypeToken().toString()) && !subject.getTypeToken().equals(TypeToken.RESFESH_TOKEN)){
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken("","",new ArrayList<>());
                        SecurityContext sc = SecurityContextHolder.getContext();
                        sc.setAuthentication(authenticationToken);
                    }
                }
            }
        }catch (Exception e){
            System.out.println("error" + e.getMessage());
        }
        filterChain.doFilter(request,response);
    }

    private String getTokenFromHeader(HttpServletRequest request){
        String strToken = request.getHeader("Authorization");
        if(StringUtils.hasText(strToken) && strToken.startsWith("Bearer ")){
            return strToken.substring(7);
        }else {
            return null;
        }
    }
}
