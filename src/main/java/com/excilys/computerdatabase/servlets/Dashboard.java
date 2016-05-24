package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Autowired
    @Qualifier("servicePage")
    public ServicePage sp;

    @Autowired
    @Qualifier("serviceComputer")
    public ServiceComputer sc;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // response.getWriter().append("Served at:
        // ").append(request.getContextPath());

        int itemsPage = RequestAnalyzer.getInstance().analyzeInt(request.getParameter("l"), 10);
        int page = RequestAnalyzer.getInstance().analyzeInt(request.getParameter("p"), 1);
        String search = RequestAnalyzer.getInstance().analyzeString(request.getParameter("search"), "");
        String champ = RequestAnalyzer.getInstance().analyzeString(request.getParameter("order"), "");
        String ascdesc = RequestAnalyzer.getInstance().analyzeString(request.getParameter("sort"), "ASC");
        Order order = null;
        if (!champ.equals("")) {
            order = new Order(champ, ascdesc);
        }

        Page<ComputerDTO> p = sp.createPage(page, itemsPage, search, order);
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

        String selection = RequestAnalyzer.getInstance().analyzeString(request.getParameter("selection"), "");
        String[] items = selection.split(",");

        List<Long> ls = new ArrayList<Long>();
        for (String item : items) {
            long id = RequestAnalyzer.getInstance().analyzeInt(item, 0);
            ls.add(id);
        }
        sc.delete(ls);

        request.setAttribute("success", items.length + " computer(s) successfully deleted !");
        doGet(request, response);

    }

}
