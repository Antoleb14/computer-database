package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.entity.ComputerDTO;
import com.excilys.computerdatabase.entity.Page;
import com.excilys.computerdatabase.service.Order;
import com.excilys.computerdatabase.service.RequestAnalyzer;
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
    protected String homeAction(@RequestParam Map<String, String> params, Model model) {

        // response.getWriter().append("Served at:
        // ").append(request.getContextPath());

        int itemsPage = RequestAnalyzer.getInstance().analyzeInt(params.get("l"), 10);
        int page = RequestAnalyzer.getInstance().analyzeInt(params.get("p"), 1);
        String search = RequestAnalyzer.getInstance().analyzeString(params.get("search"), "");
        String champ = RequestAnalyzer.getInstance().analyzeString(params.get("order"), "");
        String ascdesc = RequestAnalyzer.getInstance().analyzeString(params.get("sort"), "ASC");
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
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String deleteComputerAction(Model model, @RequestParam Map<String, String> params) {

        String selection = params.get("selection");
        String[] items = selection.split(",");
        List<Long> ls = new ArrayList<Long>();
        for (String item : items) {
            long id = RequestAnalyzer.getInstance().analyzeInt(item, 0);
            ls.add(id);
        }
        sc.delete(ls);

        model.addAttribute("successNumber", items.length);
        model.addAttribute("successTrad", "i18n.successlabel");
        return homeAction(params, model);

    }

}
