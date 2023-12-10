package example.myapp.servlet;

import ejb.RescuingPersonService;

import example.myapp.model.RescuingPerson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.weld.context.ejb.Ejb;

import java.io.IOException;

/**
 * Servlet implementation class NaturalPersonServlet
 */

@WebServlet("/RescuingPersonServlet")
public class RescuingPersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private RescuingPersonService rescuingPersonService;

    /**
     * @see HttpServlet#HttpServlet
     */
    public RescuingPersonServlet(){
        super();
    }

    /**
     *
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     *
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RescuingPerson res = new RescuingPerson();
        res.setFirstName("Maximilian");
        res.setPhoneNumber("01742536678");
        res.setLastName("Mustermann");
        res.setUserName("Max");
        res.setPassword("Mustermann");

        rescuingPersonService.persist(res);
    }
}
