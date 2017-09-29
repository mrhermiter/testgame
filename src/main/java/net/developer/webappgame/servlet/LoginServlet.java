package net.developer.webappgame.servlet;

import net.developer.webappgame.model.User;
import net.developer.webappgame.service.LoginService;
import net.developer.webappgame.service.LoginServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/login.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

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
            dispatcher.forward(httpServletRequest, httpServletResponse);

            return;
        }

        httpSession = httpServletRequest.getSession(false);
        httpSession.setAttribute("login", user.getLogin());

        httpServletResponse.sendRedirect("");
    }
}
