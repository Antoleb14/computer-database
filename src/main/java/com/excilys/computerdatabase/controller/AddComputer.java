package com.excilys.computerdatabase.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class AddComputer {

    @Autowired
    @Qualifier("serviceCompany")
    public ServiceCompany sc;

    @Autowired
    @Qualifier("serviceComputer")
    public ServiceComputer scp;

    @Autowired
    @Qualifier("computerDTOMapper")
    public ComputerDTOMapper mapper;

    @Autowired
    @Qualifier("validatorComputer")
    public ValidatorComputer v;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(value = "/addcomputer", method = RequestMethod.GET)
    protected String addComputerAction(Model model) {

        List<Company> lc = sc.findAll();
        model.addAttribute("listcompanies", lc);
        return "addComputer";
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(value = "/addcomputer", method = RequestMethod.POST)
    protected String addComputerValidAction(Model model, @Valid @ModelAttribute ComputerDTO cdto,
            BindingResult result) {

        // String name = params.get("name");
        // String introduced = params.get("introduced");
        // String discontinued = params.get("discontinued");
        // String company = params.get("company");
        //
        // model.addAttribute("name", name);
        // model.addAttribute("introduced", introduced);
        // model.addAttribute("discontinued", discontinued);
        // model.addAttribute("company", company);
        //
        // Map<String, String> errors = v.validate(name, introduced,
        // discontinued, company);
        // model.addAttribute("errors", errors);
        v.validate(cdto, result);

        if (result.hasErrors()) {
            HashMap<String, String> map = new HashMap<String, String>();
            List<ObjectError> err = result.getAllErrors();
            for (ObjectError error : err) {
                FieldError e = (FieldError) error;
                System.out.println(e);
                map.put(e.getField(), e.getCode());
            }
            model.addAttribute("errors", map);
            return addComputerAction(model);
        }

        if (cdto.getCompanyId() != null) {
            Company c = sc.find(Long.parseLong(cdto.getCompanyId()));
            if (c != null) {
                cdto.setCompanyName(c.getName());
            }
        }
        Computer t = mapper.dtoToObject(cdto);

        if (t == null) {
            return addComputerAction(model);
        } else {
            scp.create(t);
        }

        return "redirect:/home";
    }

}
