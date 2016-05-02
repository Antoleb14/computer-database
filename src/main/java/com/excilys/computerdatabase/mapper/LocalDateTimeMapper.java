package com.excilys.computerdatabase.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeMapper {

    private static LocalDateTimeMapper ldtm;

    /**
     * Class constructor.
     */
    private LocalDateTimeMapper() {
    }

    /**
     * Method to get instance of ComputerDb or create one if null.
     *
     * @return ComputerDB
     */
    public static LocalDateTimeMapper getInstance() {
        if (ldtm == null) {
            synchronized (LocalDateTimeMapper.class) {
                if (ldtm == null) {
                    ldtm = new LocalDateTimeMapper();
                }
            }
        }
        return ldtm;
    }

    public LocalDateTime map(String date) {
        LocalDateTime res = null;
        if (!date.equals("")) {
            res = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).atStartOfDay();
        }
        return res;
    }

    public String unmap(LocalDateTime date) {
        String res = "";
        if (date != null) {
            res = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        return res;
    }

}
