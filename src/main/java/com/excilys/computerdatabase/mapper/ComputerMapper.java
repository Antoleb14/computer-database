package com.excilys.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.MapperException;

public class ComputerMapper implements IMapper<Computer> {

    private static ComputerMapper cpm = null;

    /**
     * Class constructor.
     */
    private ComputerMapper() {
    }

    /**
     * Method to get instance of ComputerMapper or create one if null.
     *
     * @return ComputerMapper
     */
    public static ComputerMapper getInstance() {
        if (cpm == null) {
            synchronized (ComputerMapper.class) {
                if (cpm == null) {
                    cpm = new ComputerMapper();
                }
            }
        }
        return cpm;
    }

    @Override
    public Computer map(ResultSet rs) {
        Company company = null;
        Computer c = null;
        LocalDateTime introduced = null;
        LocalDateTime discontinued = null;
        try {
            if (rs.next()) {
                if (rs.getString(3) != null) {
                    Timestamp t = Timestamp.valueOf(rs.getString(3));
                    introduced = t.toLocalDateTime();
                }
                if (rs.getString(4) != null) {
                    Timestamp t = Timestamp.valueOf(rs.getString(4));
                    discontinued = t.toLocalDateTime();
                }
                company = new Company(rs.getLong(6), rs.getString(7));

                c = new Computer.ComputerBuilder().id(rs.getLong(1)).name(rs.getString(2)).introduced(introduced)
                        .discontinued(discontinued).company(company).build();
            }
        } catch (SQLException e) {
            throw new MapperException(e);
        }
        return c;
    }

    @Override
    public List<Computer> mapAll(ResultSet rs) {
        List<Computer> list = new ArrayList<Computer>();
        try {

            while (rs.next()) {
                LocalDateTime introduced = null;
                LocalDateTime discontinued = null;
                Company company = null;
                if (rs.getString(3) != null) {
                    Timestamp t = Timestamp.valueOf(rs.getString(3));
                    introduced = t.toLocalDateTime();
                }

                if (rs.getString(4) != null) {
                    Timestamp t = Timestamp.valueOf(rs.getString(4));
                    discontinued = t.toLocalDateTime();
                }
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                if (rs.getLong(6) != 0) {
                    company = new Company(rs.getLong(6), rs.getString(7));
                }

                Computer c = new Computer.ComputerBuilder().id(id).name(name).introduced(introduced)
                        .discontinued(discontinued).company(company).build();

                list.add(c);
            }
        } catch (SQLException e) {
            throw new MapperException(e);
        }
        return list;
    }

}
