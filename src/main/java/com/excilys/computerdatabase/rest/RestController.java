package com.excilys.computerdatabase.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.service.Order;
import com.excilys.computerdatabase.service.RequestAnalyzer;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
@ResponseBody
public class RestController {

    @Autowired
    private ServiceComputer sp;

    @Autowired
    private ServiceCompany sc;

    @JsonView(View.Summary.class)
    @RequestMapping("/json/computers")
    public List<Computer> getAllComputers(@RequestParam Map<String, String> params) {
        int itemsPage = RequestAnalyzer.getInstance().analyzeInt(params.get("l"), 10);
        int page = RequestAnalyzer.getInstance().analyzeInt(params.get("p"), 1);
        String search = RequestAnalyzer.getInstance().analyzeString(params.get("search"), "");
        String champ = RequestAnalyzer.getInstance().analyzeString(params.get("order"), "");
        String ascdesc = RequestAnalyzer.getInstance().analyzeString(params.get("sort"), "ASC");
        Order order = null;
        if (!champ.equals("")) {
            order = new Order(champ, ascdesc);
        }
        return sp.findBySearch(page, itemsPage, search, order);
    }

    @JsonView(View.Summary.class)
    @RequestMapping("/json/companies")
    public List<Company> getAllCompanies(@RequestParam Map<String, String> params) {
        return sc.findAll();
    }

}
