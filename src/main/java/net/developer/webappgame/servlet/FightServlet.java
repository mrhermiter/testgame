package net.developer.webappgame.servlet;


import net.developer.webappgame.model.User;
import net.developer.webappgame.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Fight servlet
 */
@WebServlet("/fight")
public class FightServlet extends HttpServlet {

    private FightInitService fightInitService = new FightInitServiceImpl();
    private WatchService watchService=new WatchServiceImpl();

    /**
     * Generates view-page based on result of creating fight
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        watchService.startWatching();

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
        httpServletRequest.setAttribute("page",watchService.endWatching());
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }

    /**
     * Generates view-page based on result of fight
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        watchService.startWatching();

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
        httpServletRequest.setAttribute("page",watchService.endWatching());
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
