package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.exception.SQLUtilsException;
import com.excilys.computerdatabase.persistence.ComputerDB;

public class ServiceComputer implements IService<Computer> {

	private static final ComputerDB CDB = ComputerDB.getComputerDb();
	private static ServiceComputer sc = null;

	private ServiceComputer() {
	}

	public static ServiceComputer getInstance() {
		if (sc == null) {
			synchronized (ServiceComputer.class) {
				if (sc == null) {
					sc = new ServiceComputer();
				}
			}
		}
		return sc;
	}

	@Override
	public Computer find(Long id) throws DAOException {
		return CDB.find(id);
	}

	@Override
	public List<Computer> findAll() throws DAOException {
		return CDB.findAll();
	}

	@Override
	public boolean isValid(Computer t) throws DAOException {
		if (t.getIntroduced().isAfter(t.getDiscontinued())) {
			return false;
		} else if (t.getName().equals("")) {
			return false;
		}
		return true;

	}

	@Override
	public Computer update(Computer t) throws DAOException {
		if (!isValid(t)) {
			return null;
		}
		CDB.update(t);
		return t;
	}

	@Override
	public boolean delete(Computer t) throws DAOException {
		return CDB.delete(t);
	}

	@Override
	public boolean create(Computer t) throws DAOException {
		if (!isValid(t)) {
			return false;
		}
		CDB.create(t);
		return true;
	}

	public List<Computer> findAll(int page, int number) throws DAOException {
		return CDB.findAll(page, number);
	}

	public int count() throws DAOException {
		return CDB.count();
	}

}
