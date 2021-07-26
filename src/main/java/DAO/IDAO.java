package DAO;

import java.util.List;

public interface IDAO<T,K>{

	//Create => insert
	//Read => finById + findAll
	//Update
	//Delete
	
	String urlBDD="jdbc:mysql://localhost:3306/ProjetAstro";
	String loginBDD="root";
	String passwordBDD="";
	
			
	public T findById(K id);
	
	public List<T> findAll();
	
	public T insert(T o);
	
	public T update(T o);
	
	public void delete(K id);
}
