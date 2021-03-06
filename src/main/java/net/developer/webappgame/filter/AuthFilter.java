package net.developer.webappgame.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Authentication filter
 */
@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * Initial filer in configuration
     * @param filterConfig configuration
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    /**
     * Filter action
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if ("true".equalsIgnoreCase(filterConfig.getInitParameter("active"))) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response=(HttpServletResponse)servletResponse;
            HttpSession httpSession=request.getSession(true);
            String servletPath = request.getServletPath();
            String login=(String) httpSession.getAttribute("login");

            if(login==null && !"/login".equals(servletPath)){
                response.sendRedirect("/login");
                return;
            }

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Filter destroyer
     */
    @Override
    public void destroy() {

    }
}
