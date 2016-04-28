package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.ValidatorException;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.validator.ValidatorComputer;

/**
 * Servlet implementation class AddComputer.
 */
public class AddComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServiceCompany sc = ServiceCompany.getInstance();
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

        ServiceComputer sc = ServiceComputer.getInstance();
        ValidatorComputer v = ValidatorComputer.getInstance();
        Computer t = null;
        try {
            t = v.validate(name, introduced, discontinued, company);
        } catch (ValidatorException e) {
            request.setAttribute("errors", e.getMessage());
            t = null;
        }

        if (t == null) {
            doGet(request, response);
            return;
        } else {
            sc.create(t);
        }

        response.sendRedirect("home");
    }

}
