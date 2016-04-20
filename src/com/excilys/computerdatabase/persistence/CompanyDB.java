package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerdatabase.entity.Company;

/**
 * DAO class for Company
 * @author excilys
 *
 */
public class CompanyDB extends EntityDB{
	
	/**
	 * Return all companies stored in the database
	 * @return List of companies
	 */
    public ArrayList<Company> findAll(){
    	connect();
    	ResultSet res = null;
    	ArrayList<Company> list = new ArrayList<Company>();
    	try{
    		res = statement.executeQuery("SELECT * FROM company ORDER BY name ASC");
    		CompanyDB companies = new CompanyDB();
    		while(res.next()){
				Company c = new Company(res.getLong(1), res.getString(2));
				list.add(c);
			}
    		companies.closeConnection();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}finally{
    		closeConnection();
    	}
    	return list;
    }

    /**
     * Find a company by ID
     * @param id ID of the company
     * @return Company
     */
	public Company find(int id) {
		connect();
		PreparedStatement prep = null;
		ResultSet res = null;
		Company c=null;
		String query = "SELECT id, name FROM company c WHERE id = ?";
		try {
			prep = db.getConnection().prepareStatement(query);
			prep.setInt(1, id);
			res = prep.executeQuery();
			if(res.next()){
				c = new Company(res.getLong(1), res.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
    	return c;
	}
    
	
}
