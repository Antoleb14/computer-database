package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;

/**
 * DAO class for Computer
 * @author excilys
 *
 */
public class ComputerDB extends EntityDB {
	
	private static ComputerDB cdb = null;
	
	private ComputerDB(){
		
	}
	

	/**
     * Method to get instance of ComputerDb or create one if null
     * @return ComputerDB
     */
    public static synchronized ComputerDB getComputerDb() {
        if ( cdb == null ) {
            cdb = new ComputerDB();
        }
        return cdb;
    }
	
	/**
	 * Method to persist a new Computer in the database
	 * @param c Object Computer
	 * @return boolean success of the operation
	 */
	public boolean create(Computer c){
		connect();
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
		}finally{
			closeConnection();
		}
    	return true;
	}
	
	/**
	 * Method to update a computer in the database
	 * @param c Computer
	 * @return boolean success of the operation
	 */
	public boolean update(Computer c){
		connect();
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
		}finally{
			closeConnection();
		}
    	return true;
	}
	
	/**
	 * Method to find a Computer by ID
	 * @param id ID of the Computer
	 * @return Computer found
	 */
	public Computer find(int id) {
		connect();
		PreparedStatement prep = null;
		ResultSet res = null;
		Company company = null;
		Computer c = null;
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		
		String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id WHERE i.id = ? ORDER BY i.name ASC";
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
				CompanyDB companies = CompanyDB.getCompanyDb();
				company = new Company(res.getLong(6), res.getString(7));
				
				c = new Computer(res.getLong(1), res.getString(2), introduced, discontinued, company);
				companies.closeConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}

    	return c;
	}
	
	/**
	 * Method to find all Computers in the database
	 * @return List of computers
	 */
	public ArrayList<Computer> findAll(){
		connect();
    	ResultSet res = null;
    	ArrayList<Computer> list = new ArrayList<Computer>();
    	try{
    		res = statement.executeQuery("SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id ORDER BY i.name ASC");
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
				Long id = res.getLong(1);
				String name = res.getString(2);
				company = new Company(res.getLong(6), res.getString(7));
				
				Computer c = new Computer(id, name, introduced, discontinued, company);
				
				list.add(c);
			}
    	}catch(SQLException e){
    		e.printStackTrace();
    	}finally{
			closeConnection();
		}
    	return list;
    }
	
	/**
	 * Method to find all computers by range for pagination
	 * @param page page requested
	 * @param number number of computers by page
	 * @return List of computers
	 */
	public ArrayList<Computer> findAll(int page, int number){
		connect();
    	ArrayList<Computer> list = new ArrayList<Computer>();
    	PreparedStatement prep = null;
		ResultSet res = null;
		String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id ORDER BY i.name ASC LIMIT ?, ?";
		
    	try{
    		prep = db.getConnection().prepareStatement(query);
    		prep.setInt(1,  page);
    		prep.setInt(2,  number);
    		res = prep.executeQuery();
    		
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
				Long id = res.getLong(1);
				String name = res.getString(2);
				company = new Company(res.getLong(6), res.getString(7));
				
				Computer c = new Computer(id, name, introduced, discontinued, company);
				
				list.add(c);
			}
    	}catch(SQLException e){
    		e.printStackTrace();
    	}finally{
			closeConnection();
		}
    	return list;
    }

	/**
	 * Method to delete a computer in the database
	 * @param cmp Computer to delete
	 * @return boolean for the success of the operation
	 */
	public boolean delete(Computer cmp) {
		connect();
		PreparedStatement prep = null;
		String query = "DELETE FROM computer WHERE id = ?";
		try{
			prep = db.getConnection().prepareStatement(query);
			prep.setLong(1, cmp.getId());
			prep.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			closeConnection();
		}
    	return true;
	}

	/**
	 * Method to return the total number of computers in the database
	 * @return integer number of computers
	 */
	public int count() {
		connect();
		PreparedStatement prep = null;
		ResultSet res = null;
		
		int nb=0;
		String query = "SELECT COUNT(*) FROM computer";
		try {
			prep = db.getConnection().prepareStatement(query);
			res = prep.executeQuery();
			if(res.next()){
				nb = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}

    	return nb;
	}
	
}
