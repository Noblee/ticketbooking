package me.nobler.spring.ticketbooking.Aspect;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter( filterName = "Authority",
        urlPatterns = {"/*"} )
@Component
public class Authority implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        System.out.println(path);
        //自动登录
        if(session.getAttribute("userdata")!=null&&"/".equals(path)) {
            response.sendRedirect("/main.html");
        }
        else if(session.getAttribute("userdata")!=null){
            chain.doFilter(req,resp );
        }
        else if(path.endsWith("index.html")
                || "/".equals(path)
                || path.endsWith("signup.html")|| path.endsWith("login")
                || path.endsWith("signup")||path.endsWith("js")
                ||path.endsWith("css")||path.endsWith("png")
                ||path.endsWith("jpg")||path.endsWith("ico")
                ||path.endsWith("eot")||path.endsWith("svg")
                ||path.endsWith("ttf")||path.endsWith("woff")
                ||path.endsWith("woff2")||path.endsWith("xml")
                ||path.endsWith("quit")){
            chain.doFilter(req,resp );
        }
        else {
            response.sendRedirect("/");
        }
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}
