package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.persistence.CompanyDB;

public class ServiceCompany implements IService<Company> {

	private static final CompanyDB CDB = CompanyDB.getCompanyDb();
	private static ServiceCompany sc = null;

	private ServiceCompany() {
	}

	public static ServiceCompany getInstance() {
		if (sc == null) {
			synchronized (ServiceComputer.class) {
				if (sc == null) {
					sc = new ServiceCompany();
				}
			}
		}
		return sc;
	}

	@Override
	public Company find(Long id) {
		return CDB.find(id);
	}

	@Override
	public List<Company> findAll() {
		return CDB.findAll();
	}

	@Override
	public boolean isValid(Company t) {
		if (t.getName().equals("")) {
			return false;
		}
		return true;

	}

	@Override
	public Company update(Company t) {
		if(!isValid(t)){
			return null;
		}
		CDB.update(t);
		return t;
	}

	@Override
	public boolean delete(Company t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Company t) {
		// TODO Auto-generated method stub
		return false;
	}
}
