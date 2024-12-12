package com.dnd.fbs.security.filterchain;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        try {
            if(session == null){
                response.sendRedirect("/account");
                return;
            }
            if(session.getAttribute("role") == null){
                response.sendRedirect("/admin/login");
                return;
            }
            if((int) session.getAttribute("role") == 1){
                response.sendRedirect("/");
                return;
            }
            if( (int) session.getAttribute("role") == 2 || (int) session.getAttribute("role") == 3){
                filterChain.doFilter(request,response);
            }
        } catch (Exception exception){
            System.out.println(exception);
        }

    }
}
