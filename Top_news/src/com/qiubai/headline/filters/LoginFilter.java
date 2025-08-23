package com.qiubai.headline.filters;

import com.qiubai.headline.common.Result;
import com.qiubai.headline.common.ResultCodeEnum;
import com.qiubai.headline.util.JwtHelper;
import com.qiubai.headline.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String token = req.getHeader("token");
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        Boolean flag = (token != null) && (!JwtHelper.isExpiration(token));
        if(flag) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            WebUtil.writeJson(resp, Result.build(null, ResultCodeEnum.NOTLOGIN));
        }

    }
}
