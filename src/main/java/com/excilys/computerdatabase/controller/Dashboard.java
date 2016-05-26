package com.excilys.computerdatabase.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.entity.ComputerDTO;
import com.excilys.computerdatabase.entity.Page;
import com.excilys.computerdatabase.service.Order;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.service.ServicePage;

/**
 * Servlet implementation class Dashboard.
 */
@Controller
public class Dashboard {

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
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    protected String homeAction(@RequestParam(value = "l", required = false, defaultValue = "10") int itemsPage,
            @RequestParam(value = "p", required = false, defaultValue = "1") int page,
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "order", required = false, defaultValue = "") String champ,
            @RequestParam(value = "sort", required = false, defaultValue = "ASC") String ascdesc, ModelMap model) {

        System.out.println("RENTRE DANS LA HOME");
        // response.getWriter().append("Served at:
        // ").append(request.getContextPath());

        // int itemsPage =
        // RequestAnalyzer.getInstance().analyzeInt(request.getParameter("l"),
        // 10);
        // int page =
        // RequestAnalyzer.getInstance().analyzeInt(request.getParameter("p"),
        // 1);
        // String search =
        // RequestAnalyzer.getInstance().analyzeString(request.getParameter("search"),
        // "");
        // String champ =
        // RequestAnalyzer.getInstance().analyzeString(request.getParameter("order"),
        // "");
        // String ascdesc =
        // RequestAnalyzer.getInstance().analyzeString(request.getParameter("sort"),
        // "ASC");
        Order order = null;
        if (!champ.equals("")) {
            order = new Order(champ, ascdesc);
        }

        Page<ComputerDTO> p = sp.createPage(page, itemsPage, search, order);
        model.addAttribute("count", p.getTotal());
        model.addAttribute("p", p);

        return "dashboard";
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    // @RequestMapping(method = RequestMethod.POST)
    // protected void doPost(HttpServletRequest request, HttpServletResponse
    // response)
    // throws ServletException, IOException {
    //
    // String selection =
    // RequestAnalyzer.getInstance().analyzeString(request.getParameter("selection"),
    // "");
    // String[] items = selection.split(",");
    //
    // List<Long> ls = new ArrayList<Long>();
    // for (String item : items) {
    // long id = RequestAnalyzer.getInstance().analyzeInt(item, 0);
    // ls.add(id);
    // }
    // sc.delete(ls);
    //
    // request.setAttribute("success", items.length + " computer(s) successfully
    // deleted !");
    // doGet(request, response);
    //
    // }

}
