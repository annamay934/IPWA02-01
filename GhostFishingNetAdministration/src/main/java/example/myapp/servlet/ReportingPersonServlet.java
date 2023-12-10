package example.myapp.servlet;

import ejb.ReportingPersonService;
import example.myapp.model.ReportingPerson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet implementation class NaturalPersonServlet
 */

@WebServlet("/ReportingPersonServlet")
public class ReportingPersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ReportingPersonService reportingPersonService;

    /**
     * @see HttpServlet#HttpServlet
     */
    public ReportingPersonServlet(){
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
        ReportingPerson rep = new ReportingPerson();
        rep.setFirstName("Anonymous");
        rep.setPhoneNumber("N. A.");
        rep.setLastName("Doe");

        reportingPersonService.persist(rep);
    }
}
