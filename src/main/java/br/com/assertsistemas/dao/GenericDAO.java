package br.com.assertsistemas.dao;

import java.util.List;

public interface GenericDAO<T> {

	public boolean insert(T objeto);
	
	public boolean update(T objeto);
	
	public boolean delete (int tId);
	
	public List <T> findAll();
	
	public T findById(int tId);	
}
