package com.excilys.computerdatabase.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.CompanyDTO;

@Component("companyDTOMapper")
@Scope("singleton")
public class CompanyDTOMapper {

    @Autowired
    @Qualifier("companyDTO")
    private CompanyDTO cdto;

    /**
     * Class constructor.
     */
    public CompanyDTOMapper() {
    }

    /**
     * Method to convert object to DTO.
     *
     * @param c
     *            Company
     * @return DTO
     */
    public CompanyDTO objetToDTO(Company c) {
        CompanyDTO dto = new CompanyDTO(c.getId().toString(), c.getName());
        return dto;
    }
}
