package com.excilys.computerdatabase.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("localdatetimemapper")
@Scope("singleton")
public class LocalDateTimeMapper {

    /**
     * Class constructor.
     */
    private LocalDateTimeMapper() {
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
