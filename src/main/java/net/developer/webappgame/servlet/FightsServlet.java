package net.developer.webappgame.servlet;


import net.developer.webappgame.service.WatchService;
import net.developer.webappgame.service.WatchServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Fights servlet
 */
@WebServlet("/fights")
public class FightsServlet extends HttpServlet {

    private WatchService watchService=new WatchServiceImpl();

    /**
     * Generates view-page fights
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        watchService.startWatching();

        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/fights.jsp");
        httpServletRequest.setAttribute("page",watchService.endWatching());
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }

}
