package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.entity.ComputerDTO;
import com.excilys.computerdatabase.entity.Page;
import com.excilys.computerdatabase.service.Order;
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

        int itemsPage = RequestAnalyzer.INSTANCE.analyzeInt(request.getParameter("l"), 10);
        int page = RequestAnalyzer.INSTANCE.analyzeInt(request.getParameter("p"), 1);
        String search = RequestAnalyzer.INSTANCE.analyzeString(request.getParameter("search"), "");
        String champ = RequestAnalyzer.INSTANCE.analyzeString(request.getParameter("order"), "");
        String ascdesc = RequestAnalyzer.INSTANCE.analyzeString(request.getParameter("sort"), "ASC");
        Order order = null;
        if (!champ.equals("")) {
            order = new Order(champ, ascdesc);
        }

        Page<ComputerDTO> p = ServicePage.INSTANCE.createPage(page, itemsPage, search, order);
        request.setAttribute("count", p.getTotal());
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

        String selection = RequestAnalyzer.INSTANCE.analyzeString(request.getParameter("selection"), "");
        String[] items = selection.split(",");

        ServiceComputer sc = ServiceComputer.INSTANCE;
        List<Long> ls = new ArrayList<Long>();
        for (String item : items) {
            long id = RequestAnalyzer.INSTANCE.analyzeInt(item, 0);
            ls.add(id);
        }
        sc.delete(ls);

        request.setAttribute("success", items.length + " computer(s) successfully deleted !");
        doGet(request, response);

    }

}
