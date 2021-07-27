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
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into compte (login,password,type) VALUES (?,?,?)");

			ps.setString(1, o.getLogin());
			ps.setString(2, o.getPassword());
			
			if(o instanceof Utilisateur) 
			{
				ps.setString(3, "utilisateur");
			}
			else if(o instanceof Admin) 
			{
				ps.setString(3, "admin");
			}
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return o;
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
				if(rs.getString("type").equals("admin")) 
				{
					 c = new Admin(rs.getInt("id"), rs.getString("login"),rs.getString("password"));
				}
				else if(rs.getString("type").equals("utilisateur"))
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