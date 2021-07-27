package DAO;

import java.util.List;

public interface IDAO<T,K>{

	//Create => insert
	//Read => finById + findAll
	//Update
	//Delete
	
	String urlBDD="jdbc:mysql://localhost:8889/ProjetAstro";
	String loginBDD="root";
	String passwordBDD="root";
	
			
	public T findById(K id);
	
	public List<T> findAll();
	
	public T insert(T o);
	
	public T update(T o);
	
	public void delete(K id);
}
