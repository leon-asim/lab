package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@WebServlet(name = "eventList", urlPatterns = "")
public class EventListServlet extends HttpServlet {

    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Event> eventList;

        if(req.getParameter("search") != null && req.getParameter("rating") != null && req.getParameter("search") != "" && req.getParameter("rating") != "") {
            eventList = eventService.searchEventsByNameAndRating(req.getParameter("search"), Double.parseDouble(req.getParameter("rating")));
        } else if(req.getParameter("search") != null && req.getParameter("search") != "") {
            eventList = eventService.searchEvents(req.getParameter("search"));
        } else if(req.getParameter("rating") != null && req.getParameter("rating") != "") {
            eventList = eventService.searchEventsByRating(Double.parseDouble(req.getParameter("rating")));
        } else
            eventList = eventService.listAll();

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("events", eventList);

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }
}
