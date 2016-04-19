package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class ComputerDB extends EntityDB {
	
	public ComputerDB(){
		connect();
	}
	
	public boolean create(Computer c){
		PreparedStatement prep = null;
		String query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES(?, ?, ?, ?)";
		try{
			prep = db.getConnection().prepareStatement(query);
			prep.setString(1, c.getName());
			if(c.getIntroduced() == null){
				prep.setNull(2, java.sql.Types.TIMESTAMP);
			}else{
				prep.setTimestamp(2, Timestamp.valueOf(c.getIntroduced()));
			}
			if(c.getDiscontinued() == null){
				prep.setNull(3, java.sql.Types.TIMESTAMP);
			}else{
				prep.setTimestamp(3, Timestamp.valueOf(c.getDiscontinued()));
			}
			if(c.getCompany() != null){
				prep.setLong(4, c.getCompany().getId());
			}else{
				prep.setNull(4, java.sql.Types.INTEGER);
			}
			
			prep.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
    	return true;
	}
	
	public boolean update(Computer c){
		PreparedStatement prep = null;
		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
		try{
			prep = db.getConnection().prepareStatement(query);
			prep.setString(1, c.getName());
			if(c.getIntroduced() == null){
				prep.setNull(2, java.sql.Types.TIMESTAMP);
			}else{
				prep.setTimestamp(2, Timestamp.valueOf(c.getIntroduced()));
			}
			if(c.getDiscontinued() == null){
				prep.setNull(3, java.sql.Types.TIMESTAMP);
			}else{
				prep.setTimestamp(3, Timestamp.valueOf(c.getDiscontinued()));
			}
			
			if(c.getCompany() != null){
				prep.setLong(4, c.getCompany().getId());
			}else{
				prep.setNull(4, java.sql.Types.INTEGER);
			}
			
			prep.setLong(5, c.getId());
			prep.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
    	return true;
	}
	
	
	public ResultSet findByName(String name){
		PreparedStatement prep = null;
		ResultSet res = null;
		String query = "SELECT * FROM computer WHERE name = ?";
		try{
			prep = db.getConnection().prepareStatement(query);
			prep.setString(1, name);
			res = prep.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
    	return res;
    }

	public Computer find(int id) {
		PreparedStatement prep = null;
		ResultSet res = null;
		Company company = null;
		Computer c = null;
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		
		String query = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c WHERE c.id = ?";
		try {
			prep = db.getConnection().prepareStatement(query);
			prep.setInt(1, id);
			res = prep.executeQuery();
			if(res.next()){
				if(res.getString(3) != null){
					 Timestamp t = Timestamp.valueOf(res.getString(3));
					 introduced = t.toLocalDateTime();
				}
				if(res.getString(4) != null){
					 Timestamp t = Timestamp.valueOf(res.getString(4));
					 discontinued = t.toLocalDateTime();
				}
				CompanyDB companies = new CompanyDB();
				company = companies.find(res.getInt(5));
				
				c = new Computer(res.getLong(1), res.getString(2), introduced, discontinued, company);
				companies.closeConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	return c;
	}
	
	public ArrayList<Computer> findAll(){
    	ResultSet res = null;
    	ArrayList<Computer> list = new ArrayList<Computer>();
    	try{
    		res = statement.executeQuery("SELECT * FROM computer ORDER BY name ASC");
    		CompanyDB companies = new CompanyDB();
    		while(res.next()){
				LocalDateTime introduced=null;
				LocalDateTime discontinued=null;
				Company company = null;
				if(res.getString(3) != null){
					 Timestamp t = Timestamp.valueOf(res.getString(3));
					 introduced = t.toLocalDateTime();
				}
				
				if(res.getString(4) != null){
					 Timestamp t = Timestamp.valueOf(res.getString(4));
					 discontinued = t.toLocalDateTime();
				}
				
				company = companies.find(res.getInt(5));
				
				Computer c = new Computer(res.getLong(1), res.getString(2), introduced, discontinued, company);
				
				
				list.add(c);
			}
    		companies.closeConnection();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return list;
    }

	public boolean delete(Computer cmp) {
		PreparedStatement prep = null;
		String query = "DELETE FROM computer WHERE id = ?";
		try{
			prep = db.getConnection().prepareStatement(query);
			prep.setLong(1, cmp.getId());
			prep.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
    	return true;
	}
	
}
