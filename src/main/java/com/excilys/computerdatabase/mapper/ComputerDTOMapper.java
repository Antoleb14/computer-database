package com.excilys.computerdatabase.mapper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.entity.ComputerDTO;

public class ComputerDTOMapper {

    private static ComputerDTOMapper cdtom;

    /**
     * Class constructor.
     */
    private ComputerDTOMapper() {
    }

    /**
     * Method to get instance of ComputerDb or create one if null.
     *
     * @return ComputerDB
     */
    public static ComputerDTOMapper getInstance() {
        if (cdtom == null) {
            synchronized (ComputerDTOMapper.class) {
                if (cdtom == null) {
                    cdtom = new ComputerDTOMapper();
                }
            }
        }
        return cdtom;
    }

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
        if (c.getIntroduced() != null) {
            introduced = c.getIntroduced().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        if (c.getDiscontinued() != null) {
            discontinued = c.getDiscontinued().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        if (c.getCompany() != null) {
            company = c.getCompany().getName();
        }
        ComputerDTO dto = new ComputerDTO.ComputerDTOBuilder().id(c.getId().toString()).name(c.getName())
                .introduced(introduced).discontinued(discontinued).company(company).build();
        return dto;
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
