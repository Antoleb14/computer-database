package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.mapper.ComputerDTOMapper;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.validator.ValidatorComputer;

/**
 * Servlet implementation class AddComputer.
 */
public class AddComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    @Qualifier("serviceCompany")
    public ServiceCompany sc;

    @Autowired
    @Qualifier("serviceComputer")
    public ServiceComputer scp;

    @Autowired
    @Qualifier("computerDTOMapper")
    public ComputerDTOMapper cdto;

    @Autowired
    @Qualifier("validatorComputer")
    public ValidatorComputer v;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Company> lc = sc.findAll();

        request.setAttribute("listcompanies", lc);

        getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String introduced = request.getParameter("introduced");
        String discontinued = request.getParameter("discontinued");
        String company = request.getParameter("company");

        request.setAttribute("name", name);
        request.setAttribute("introduced", introduced);
        request.setAttribute("discontinued", discontinued);
        request.setAttribute("company", company);

        List<String> errors = v.validate(name, introduced, discontinued, company);
        request.setAttribute("errors", errors);

        if (!errors.isEmpty()) {
            doGet(request, response);
            return;
        }
        Company c = sc.find(Long.parseLong(company));
        Computer t = cdto.dtoToObject(name, introduced, discontinued, c.getId().toString(), c.getName());

        if (t == null) {
            doGet(request, response);
            return;
        } else {
            scp.create(t);
        }

        response.sendRedirect("home");
    }

}
