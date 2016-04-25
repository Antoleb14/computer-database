package com.excilys.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.MapperException;

public class CompanyMapper implements IMapper<Company> {

    private static CompanyMapper cpm;

    /**
     * Class constructor.
     */
    private CompanyMapper() {
    }

    /**
     * Method to get instance of ComputerDb or create one if null.
     *
     * @return ComputerDB
     */
    public static CompanyMapper getInstance() {
        if (cpm == null) {
            synchronized (CompanyMapper.class) {
                if (cpm == null) {
                    cpm = new CompanyMapper();
                }
            }
        }
        return cpm;
    }

    @Override
    public Company map(ResultSet rs) {
        Company c = null;
        try {
            c = new Company(rs.getLong(1), rs.getString(2));
        } catch (SQLException e) {

        }
        return c;
    }

    @Override
    public List<Company> mapAll(ResultSet rs) {
        List<Company> list = new ArrayList<Company>();
        try {
            while (rs.next()) {
                Company c = new Company(rs.getLong(1), rs.getString(2));
                list.add(c);
            }
        } catch (SQLException e) {
            throw new MapperException(e);
        }
        return list;
    }

}
