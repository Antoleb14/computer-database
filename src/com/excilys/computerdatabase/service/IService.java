package com.excilys.computerdatabase.service;

import java.util.List;

public interface IService<T> {

	public T find(Long id);
	public List<T> findAll();
	public T update(T t);
	public boolean isValid(T t);
	public boolean delete(T t);
	public boolean create(T t);
}
