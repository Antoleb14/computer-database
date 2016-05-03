package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.entity.ComputerDTO;
import com.excilys.computerdatabase.entity.Page;
import com.excilys.computerdatabase.service.RequestAnalyzer;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.service.ServicePage;

/**
 * Servlet implementation class Dashboard.
 */
public class Dashboard extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // response.getWriter().append("Served at:
        // ").append(request.getContextPath());

        ServiceComputer sc = ServiceComputer.INSTANCE;
        List<Computer> listComputers = sc.findAll();
        request.setAttribute("computers", listComputers);

        int count = sc.count();
        request.setAttribute("count", count);

        int itemsPage = RequestAnalyzer.INSTANCE.analyzeInt(request.getParameter("l"));
        int page = RequestAnalyzer.INSTANCE.analyzeInt(request.getParameter("p"));

        ServicePage sp = new ServicePage();
        Page<ComputerDTO> p = sp.createPage(page, itemsPage);
        request.setAttribute("p", p);

        getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getParameterMap());
    }

}
