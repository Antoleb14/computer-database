package com.excilys.computerdatabase.controller;

import java.util.HashMap;
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
@Controller
public class EditComputer {

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

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(value = "/editcomputer", method = RequestMethod.GET)
    protected String editFormAction(Model model, @RequestParam(value = "computerId", required = true) long id) {

        // Long.parseLong(request.getParameter("computerId"));

        Computer edit = scp.find(id);
        ComputerDTO c = cdto.objetToDTO(edit);
        model.addAttribute("c", c);
        List<Company> lc = sc.findAll();

        model.addAttribute("listcompanies", lc);
        return "editComputer";
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(value = "/editcomputer", method = RequestMethod.POST)
    protected String editAction(Model model, @RequestParam Map<String, String> params) {
        String id = params.get("id");
        String name = params.get("name");
        String introduced = params.get("introduced");
        String discontinued = params.get("discontinued");
        String company = params.get("company");

        model.addAttribute("name", name);
        model.addAttribute("introduced", introduced);
        model.addAttribute("discontinued", discontinued);
        model.addAttribute("company", company);

        HashMap<String, String> errors = v.validate(id, name, introduced, discontinued, company);
        model.addAttribute("errors", errors);
        if (!errors.isEmpty()) {
            return editFormAction(model, Long.parseLong(id));
        }

        Company c = sc.find(Long.parseLong(company));

        Computer t = cdto.dtoToObject(id, name, introduced, discontinued, c.getId().toString(), c.getName());

        if (t == null) {
            return editFormAction(model, Long.parseLong(id));
        } else {
            scp.update(t);
        }

        return "redirect:/home";
    }

}
