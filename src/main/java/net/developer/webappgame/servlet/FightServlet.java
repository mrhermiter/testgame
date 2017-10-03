package net.developer.webappgame.servlet;


import net.developer.webappgame.model.User;
import net.developer.webappgame.service.FightInitService;
import net.developer.webappgame.service.FightInitServiceImpl;
import net.developer.webappgame.service.FightService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/fight")
public class FightServlet extends HttpServlet {
    private FightInitService fightInitService = new FightInitServiceImpl();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        FightService fightService;
        HttpSession httpSession = httpServletRequest.getSession();
        fightService = (FightService) httpSession.getAttribute("fight");

        if (fightService == null) {
            User user = (User) httpSession.getAttribute("user");
            fightService = fightInitService.init(user);
            if (fightService != null) {
                httpSession.setAttribute("fight", fightService);
            }
        }

        dispatcher = httpServletRequest.getRequestDispatcher("/fight.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession httpSession = httpServletRequest.getSession();
        FightService fightService = (FightService) httpSession.getAttribute("fight");

        if ("hit".equals(httpServletRequest.getParameter("send"))) {

            List<String> log = fightService.hit();
            httpServletRequest.setAttribute("log", log);
        }

        if ("exit".equals(httpServletRequest.getParameter("send"))) {
            httpSession.setAttribute("user", fightService.getSelfUser());
            httpSession.setAttribute("fight", null);
            httpServletResponse.sendRedirect("");

            return;
        }

        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/fight.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
