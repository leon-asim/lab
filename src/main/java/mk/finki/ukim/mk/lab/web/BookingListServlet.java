package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@WebServlet(name = "bookingList", urlPatterns = "/bookings")
public class BookingListServlet extends HttpServlet {

    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<EventBooking> eventBookings;

        if(req.getParameter("searchText") != null && req.getParameter("searchText") != "") {
            eventBookings = eventBookingService.getBookingsByName(req.getParameter("searchText"));
        } else
            eventBookings = eventBookingService.eventBookings();

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("bookings", eventBookings);

        springTemplateEngine.process("listBooking.html", context, resp.getWriter());
    }
}
