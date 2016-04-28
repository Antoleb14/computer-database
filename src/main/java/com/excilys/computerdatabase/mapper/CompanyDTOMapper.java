package com.excilys.computerdatabase.mapper;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.CompanyDTO;

public class CompanyDTOMapper {

    private static CompanyDTOMapper cdtom;

    /**
     * Class constructor.
     */
    private CompanyDTOMapper() {
    }

    /**
     * Method to get instance of ComputerDb or create one if null.
     *
     * @return ComputerDB
     */
    public static CompanyDTOMapper getInstance() {
        if (cdtom == null) {
            synchronized (CompanyDTOMapper.class) {
                if (cdtom == null) {
                    cdtom = new CompanyDTOMapper();
                }
            }
        }
        return cdtom;
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
