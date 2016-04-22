package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.mapper.CompanyMapper;

/**
 * DAO class for Company
 * 
 * @author excilys
 *
 */
public class CompanyDB extends EntityDB<Company> {

	private static CompanyDB cdb = null;

	private CompanyDB() {
	}

	/**
	 * Method to get instance of ComputerDb or create one if null
	 * 
	 * @return ComputerDB
	 */
	public static synchronized CompanyDB getCompanyDb() {
		if (cdb == null) {
			cdb = new CompanyDB();
		}
		return cdb;
	}

	/**
	 * Return all companies stored in the database
	 * 
	 * @return List of companies
	 */
	@Override
	public List<Company> findAll() {
		connect();
		ResultSet res = null;
		List<Company> list = new ArrayList<Company>();
		try {
			Statement statement = db.createStatement();
			res = statement.executeQuery("SELECT * FROM company ORDER BY name ASC");
			list = CompanyMapper.getInstance().mapAll(res);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection();
		}
		return list;
	}

	/**
	 * Find a company by ID
	 * 
	 * @param id
	 *            ID of the company
	 * @return Company
	 */
	@Override
	public Company find(Long id) {
		connect();
		PreparedStatement prep = null;
		ResultSet res = null;
		Company c = null;
		String query = "SELECT id, name FROM company c WHERE id = ?";
		try {
			prep = db.prepareStatement(query);
			prep.setLong(1, id);
			res = prep.executeQuery();
			if (res.next()) {
				c = CompanyMapper.getInstance().map(res);
			}
			prep.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection();
		}
		return c;
	}

	@Override
	public Company update(Company c) {
		// Not Implemented
		return null;
	}

	@Override
	public Company create(Company c) {
		// Not Implemented
		return null;
	}

	@Override
	public boolean delete(Company c) {
		// Not Implemented
		return false;
	}

}
