package com.excilys.computerdatabase.mapper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.entity.ComputerDTO;

public enum ComputerDTOMapper {

    INSTANCE;

    /**
     * Object to DTO.
     *
     * @param c
     *            Computer
     * @return ComputerDTO
     */
    public ComputerDTO objetToDTO(Computer c) {
        String introduced = null;
        String discontinued = null;
        String company = null;
        String companyid = null;
        if (c.getIntroduced() != null) {
            introduced = c.getIntroduced().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        if (c.getDiscontinued() != null) {
            discontinued = c.getDiscontinued().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        if (c.getCompany() != null) {
            company = c.getCompany().getName();
            companyid = c.getCompany().getId().toString();
        }
        ComputerDTO dto = new ComputerDTO.ComputerDTOBuilder().id(c.getId().toString()).name(c.getName())
                .introduced(introduced).discontinued(discontinued).company(company).companyid(companyid).build();
        return dto;
    }

    public Computer dtoToObject(String id, String name, String intro, String disc, String cid, String cname) {
        Company c = new Company(Long.parseLong(cid), cname);
        LocalDateTimeMapper dtm = LocalDateTimeMapper.getInstance();
        Computer t = new Computer.ComputerBuilder().id(Long.parseLong(id)).name(name).introduced(dtm.map(intro))
                .discontinued(dtm.map(disc)).company(c).build();
        return t;
    }

    public Computer dtoToObject(String name, String intro, String disc, String cid, String cname) {
        Company c = new Company(Long.parseLong(cid), cname);
        LocalDateTimeMapper dtm = LocalDateTimeMapper.getInstance();
        Computer t = new Computer.ComputerBuilder().name(name).introduced(dtm.map(intro)).discontinued(dtm.map(disc))
                .company(c).build();
        return t;
    }

    /**
     * List of objects to list of DTOs.
     *
     * @param elements
     *            list of objects
     * @return list of DTOs
     */
    public ArrayList<ComputerDTO> listObjetToDTO(ArrayList<Computer> elements) {
        ArrayList<ComputerDTO> listdto = new ArrayList<ComputerDTO>();
        for (Computer c : elements) {
            listdto.add(this.objetToDTO(c));
        }
        return listdto;
    }
}
