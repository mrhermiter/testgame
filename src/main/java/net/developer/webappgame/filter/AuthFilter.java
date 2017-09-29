package net.developer.webappgame.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            System.out.println("filter is work");
        }

    }

    @Override
    public void destroy() {

    }
}
