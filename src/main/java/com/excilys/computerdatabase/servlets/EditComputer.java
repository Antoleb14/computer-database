package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.entity.ComputerDTO;
import com.excilys.computerdatabase.mapper.ComputerDTOMapper;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.validator.ValidatorComputer;

/**
 * Servlet implementation class AddComputer.
 */
public class EditComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("computerId"));

        ServiceComputer scomp = ServiceComputer.INSTANCE;

        Computer edit = scomp.find(id);
        ComputerDTO cdto = ComputerDTOMapper.INSTANCE.objetToDTO(edit);
        request.setAttribute("c", cdto);
        ServiceCompany sc = ServiceCompany.INSTANCE;
        List<Company> lc = sc.findAll();

        request.setAttribute("listcompanies", lc);

        getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String introduced = request.getParameter("introduced");
        String discontinued = request.getParameter("discontinued");
        String company = request.getParameter("company");

        request.setAttribute("name", name);
        request.setAttribute("introduced", introduced);
        request.setAttribute("discontinued", discontinued);
        request.setAttribute("company", company);

        ServiceComputer sc = ServiceComputer.INSTANCE;
        ValidatorComputer v = ValidatorComputer.INSTANCE;
        List<String> errors = v.validate(id, name, introduced, discontinued, company);
        request.setAttribute("errors", errors);
        if (!errors.isEmpty()) {
            doGet(request, response);
            return;
        }

        Company c = ServiceCompany.INSTANCE.find(Long.parseLong(company));

        Computer t = ComputerDTOMapper.INSTANCE.dtoToObject(id, name, introduced, discontinued, c.getId().toString(),
                c.getName());

        if (t == null) {
            doGet(request, response);
            return;
        } else {
            sc.update(t);
        }

        response.sendRedirect("home");
    }

}
