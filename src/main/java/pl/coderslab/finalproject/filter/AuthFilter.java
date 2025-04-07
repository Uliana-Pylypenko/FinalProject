package pl.coderslab.finalproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
//@WebFilter("/user/*")
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        System.out.println("AAAAAAAA");
        if (session.getAttribute("user") == null) {
            httpResponse.sendRedirect("/login");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    public void destroy() {}
    public void init(FilterConfig config) throws ServletException {
    }
}
