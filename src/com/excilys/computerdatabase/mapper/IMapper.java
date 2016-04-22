package com.excilys.computerdatabase.mapper;

import java.sql.ResultSet;
import java.util.List;

public interface IMapper<T> {

	public T map(ResultSet rs);

	public List<T> mapAll(ResultSet rs);

}
