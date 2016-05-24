package com.excilys.computerdatabase.validator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.exception.ValidatorException;
import com.excilys.computerdatabase.mapper.LocalDateTimeMapper;
import com.excilys.computerdatabase.service.ServiceCompany;

@Component("validatorComputer")
public class ValidatorComputer implements IValidator<Computer> {

    @Autowired
    @Qualifier("serviceCompany")
    private ServiceCompany spcomp;

    @Autowired
    @Qualifier("localdatetimemapper")
    private LocalDateTimeMapper ldt;

    /**
     * Default class constructor.
     */
    public ValidatorComputer() {
    }

    /**
     * Method to know if a computer is valid.
     *
     * @param t
     *            Computer
     * @return boolean
     * @throws DAOException
     */
    @Override
    public boolean isValid(Computer t) {
        LocalDateTime begin = (new Timestamp(0)).toLocalDateTime();
        LocalDateTime end = LocalDate.of(2038, 01, 18).atStartOfDay();

        if (t.getIntroduced() != null && t.getDiscontinued() != null) {
            if (t.getIntroduced().isAfter(t.getDiscontinued())) {
                LOG.error("Computer is not valid (introduction is after end) : " + t);
                return false;
            }
        } else if (t.getIntroduced() != null
                && (begin.isAfter(t.getIntroduced()) || begin.isEqual(t.getIntroduced()))) {
            LOG.error("Computer is not valid (Date of introduction is before timestamp start) : " + t);
            return false;
        } else if (t.getDiscontinued() != null
                && (end.isBefore(t.getDiscontinued()) || end.isEqual(t.getDiscontinued()))) {
            LOG.error("Computer is not valid (Date of end is after timestamp max value) : " + t);
            return false;
        } else if (t.getName().equals("")) {
            LOG.error("Computer is not valid (name is empty) : " + t);
            return false;
        }

        return true;
    }

    /**
     * Method to validate a computer.
     *
     * @param name
     *            name of the computer
     * @param introduced
     *            date of introduction
     * @param discontinued
     *            date of end
     * @param company
     *            id of the company
     * @throws ValidatorException
     * @return Computer
     */
    public List<String> validate(String name, String introduced, String discontinued, String company)
            throws ValidatorException {

        List<String> resls = new ArrayList<String>();
        name = name.trim();

        if (!name.matches("^[a-zA-Z0-9\\-\\ &]+$") || name.length() == 0) {
            resls.add("The name is incorrect");
        }

        LocalDateTime intro = null;
        LocalDateTime disc = null;
        try {
            if (!introduced.equals("")) {
                intro = ldt.map(introduced);
            }
        } catch (Exception e) {
            resls.add("The introduced date is incorrect and must be format dd-mm-yyyy");
        }

        try {
            if (!discontinued.equals("")) {
                disc = ldt.map(discontinued);
            }
        } catch (Exception e) {
            resls.add("The discontinued date is incorrect and must be format dd-mm-yyyy");
        }

        Company c = null;
        if (!company.equals("0")) {
            c = spcomp.find(new Long(company));
            if (c == null) {
                resls.add("The company doesn't exist !");
            }
        }
        Computer t = new Computer.ComputerBuilder().name(name).introduced(intro).discontinued(disc).company(c).build();

        if (!isValid(t)) {
            resls.add("The computer contains invalid datas : Check your dates !");
        }
        return resls;

    }

    /**
     * Method to validate a computer.
     *
     * @param name
     *            name of the computer
     * @param introduced
     *            date of introduction
     * @param discontinued
     *            date of end
     * @param company
     *            id of the company
     * @throws ValidatorException
     * @return Computer
     */
    public List<String> validate(String id, String name, String introduced, String discontinued, String company)
            throws ValidatorException {

        List<String> resls = new ArrayList<String>();
        if (!id.trim().matches("^[1-9][0-9]*$")) {
            resls.add("Invalid ID");
        }
        long computerid = Long.parseLong(id);
        name = name.trim();

        if (!name.matches("^[a-zA-Z0-9\\-\\ &]+$") || name.length() == 0) {
            resls.add("The name is incorrect");
        }

        LocalDateTime intro = null;
        LocalDateTime disc = null;
        try {
            if (!introduced.equals("")) {
                intro = ldt.map(introduced);
            }
        } catch (Exception e) {
            resls.add("The introduced date is incorrect and must be format dd-mm-yyyy");
        }

        try {
            if (!discontinued.equals("")) {
                disc = ldt.map(discontinued);
            }
        } catch (Exception e) {
            resls.add("The discontinued date is incorrect and must be format dd-mm-yyyy");
        }

        Company c = null;
        if (!company.equals("0")) {
            c = spcomp.find(new Long(company));
            if (c == null) {
                resls.add("The company doesn't exist !");
            }
        }
        Computer t = new Computer.ComputerBuilder().id(computerid).name(name).introduced(intro).discontinued(disc)
                .company(c).build();

        if (!isValid(t)) {
            resls.add("The computer contains invalid datas.");
        }
        return resls;
    }

}
