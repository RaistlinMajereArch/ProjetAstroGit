package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.IDAO;
import metier.CorpsCeleste;
import metier.Etoile;
import metier.Planete;
import metier.Position;
import metier.Satellite;

public class DAOPositions implements IDAO<Position,Integer> {
	
	public List<Position> findByTimestep(Integer t) {
		List<Position> positions = new ArrayList<Position>();
		Position p= null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from positions where id_timestep=?");
			ps.setInt(1, t);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				p=new Position(rs.getInt("id_timeStep"),rs.getInt("id_corpsCeleste"),rs.getDouble("x"),rs.getDouble("y"));
				positions.add(p);
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return positions;
	}
	
	public List<Position> findByIdCorps(Integer id) {
		List<Position> positions = new ArrayList<Position>();
		Position p= null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from positions where id_corpsCeleste=? ORDER BY id_timeStep");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				p=new Position(rs.getInt("id_timeStep"),rs.getInt("id_corpsCeleste"),rs.getDouble("x"),rs.getDouble("y"));
				positions.add(p);
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return positions;
	}
	@Override
	public Position findById(Integer id) {
		Position p= null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from positions id=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				p=new Position(rs.getInt("id_timeStep"),rs.getInt("id_corpsCeleste"),rs.getDouble("x"),rs.getDouble("y"));
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Position> findAll() {
		List<Position> positions = new ArrayList<Position>();
		Position p= null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from positions");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				p=new Position(rs.getInt("id_timeStep"),rs.getInt("id_corpsCeleste"),rs.getDouble("x"),rs.getDouble("y"));
				positions.add(p);
			}

			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return positions;
	}


	@Override
	public Position insert(Position p) {
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("INSERT into positions (id_timeStep,id_corpsCeleste,x,y) VALUES (?,?,?,?)");

			ps.setInt(1, p.getId_timeStep());
			ps.setInt(2, p.getId_corpsCeleste());
			ps.setDouble(3, p.getX());
			ps.setDouble(4, p.getY());
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(Exception e) 
		{	
			System.out.println(p);
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public Position update(Position p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE positions set id_timeStep=?,id_corpsCeleste=?,x=?,y=? where id=?");

			ps.setInt(1, p.getId_timeStep());
			ps.setInt(2, p.getId_corpsCeleste());
			ps.setDouble(3, p.getX());
			ps.setDouble(4, p.getY());
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return p;
	}

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from positions where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public void deleteAll() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from positions");
			ps.executeUpdate();
			ps = conn.prepareStatement("ALTER TABLE positions AUTO_INCREMENT = 1");
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}

}


