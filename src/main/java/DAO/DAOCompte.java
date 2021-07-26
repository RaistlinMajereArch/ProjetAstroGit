package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import metier.Admin;
import metier.Utilisateur;
import metier.Compte;

public class DAOCompte implements IDAO<Compte,Integer>{
	
	

	@Override
	public Compte findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte insert(Compte o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compte update(Compte o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	public static Compte seConnecter(String login,String password) 
	{
		Compte c=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
 
			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where login=? and password=?");
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				if(rs.getString("type_compte").equals("admin")) 
				{
					 c = new Admin(rs.getInt("id"), rs.getString("login"),rs.getString("password"));
				}
				else if(rs.getString("type_compte").equals("client"))
				{
					 c = new Utilisateur(rs.getInt("id"), rs.getString("login"),rs.getString("password"));
				}
			}
			
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

}
