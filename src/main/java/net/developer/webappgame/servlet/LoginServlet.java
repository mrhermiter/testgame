package net.developer.webappgame.servlet;

import net.developer.webappgame.model.User;
import net.developer.webappgame.service.LoginService;
import net.developer.webappgame.service.LoginServiceImpl;
import net.developer.webappgame.service.WatchService;
import net.developer.webappgame.service.WatchServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();
    private WatchService watchService=new WatchServiceImpl();


    /**
     * Generates view-page login
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        watchService.startWatching();
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/login.jsp");
        httpServletRequest.setAttribute("page",watchService.endWatching());
        dispatcher.forward(httpServletRequest, httpServletResponse);

    }

    /**
     * Generates view-page or redirect based on result authentication
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        watchService.startWatching();

        User user = new User();
        HttpSession httpSession;

        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        String message = "";

        user.setLogin(login);
        user.setPassword(password);
        user = loginService.loginUser(user);

        if (user == null) {
            message = "Bad login/password";
            httpServletRequest.setAttribute("message", message);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/login.jsp");
            httpServletRequest.setAttribute("page",watchService.endWatching());
            dispatcher.forward(httpServletRequest, httpServletResponse);

            return;
        }

        httpSession = httpServletRequest.getSession(false);
        httpSession.setAttribute("login", user.getLogin());
        httpSession.setAttribute("user", user);
        httpServletResponse.sendRedirect("");
    }
}
