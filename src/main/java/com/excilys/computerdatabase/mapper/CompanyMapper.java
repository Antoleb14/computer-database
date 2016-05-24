package com.excilys.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.MapperException;

@Component("companyMapper")
@Scope("singleton")
public class CompanyMapper implements IMapper<Company> {

    /**
     * Constructor of the class.
     */
    public CompanyMapper() {
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
