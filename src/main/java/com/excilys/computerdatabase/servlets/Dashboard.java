package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.entity.Page;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.service.ServicePage;

/**
 * Servlet implementation class Dashboard
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

        ServiceComputer sc = ServiceComputer.getInstance();
        List<Computer> listComputers = sc.findAll();
        request.setAttribute("computers", listComputers);

        int count = sc.count();
        request.setAttribute("count", count);

        int itemsPage = 10;
        try {
            String limit = request.getParameter("l");
            if (limit != null) {
                itemsPage = Integer.parseInt(limit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int page = 1;
        try {
            if (request.getParameter("p") != null) {
                page = Integer.parseInt(request.getParameter("p"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ServicePage sp = new ServicePage();
        Page<Computer> p = sp.createPage(page, itemsPage);

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
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
