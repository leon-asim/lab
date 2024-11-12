package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@WebServlet(name = "eventBooking", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {

    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String eventName = req.getParameter("eventName");
        String user = req.getParameter("userName");
        String tickets = req.getParameter("numTickets");

        context.setVariable("eventName", eventName);
        context.setVariable("tickets", tickets);
        context.setVariable("user", user);

        eventBookingService.placeBooking(eventName, user, Long.parseLong(tickets));

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }
}
