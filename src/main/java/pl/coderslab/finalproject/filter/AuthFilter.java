package pl.coderslab.finalproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import pl.coderslab.finalproject.dto.UserDTO;
import pl.coderslab.finalproject.entity.User;

import java.io.IOException;

//@Component
//@WebFilter("/user/*")
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String requestURI = httpRequest.getRequestURI();
        //User user = (User) session.getAttribute("user");
        UserDTO user = (UserDTO) session.getAttribute("userDTO");
        System.out.println("USER NULL: " + user==null);

        if (user == null) {
            if (requestURI.startsWith("/user")
                    || requestURI.startsWith("/admin")
                    || requestURI.contains("/update/")
                    || requestURI.contains("/delete/")
                    || requestURI.contains("/add/")
                    || requestURI.contains("/create/")) {

                httpResponse.sendRedirect("/login");
            }
            
        } else {
            if (((user.isAdmin() && requestURI.startsWith("/user")))
                    || (!user.isAdmin() && requestURI.startsWith("/admin")))  {
                httpResponse.sendRedirect("/access-denied");
            }

        }
        filterChain.doFilter(request, response);
    }

    public void destroy() {}
    public void init(FilterConfig config) throws ServletException {
    }
}
