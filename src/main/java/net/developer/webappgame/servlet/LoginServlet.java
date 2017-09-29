package net.developer.webappgame.servlet;

import net.developer.webappgame.model.User;
import net.developer.webappgame.repository.UserRepository;
import net.developer.webappgame.repository.UserRepositoryImpl;
import net.developer.webappgame.service.LoginService;
import net.developer.webappgame.service.LoginServiceImpl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService=new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/login.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}
