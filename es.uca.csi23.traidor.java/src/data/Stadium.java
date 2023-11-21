package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import util.Database;

public class Stadium {
	private Integer _iId;
	public Integer getId() { return _iId; }
	
	private String _sName;
	public String getName() { return _sName; }

	//Precondición: sName != null && sName != ""
	public void setName(String sName) {
		
		if(sName == null || sName.equals("")) throw new IllegalArgumentException("El nombre no puede ser nulo o estar vacío.");
		_sName = sName;
	}
	
	private Date _dtDeletedAt = null;
	public Date getDeletedAt(){ return _dtDeletedAt; };

	public Stadium(String sName) { this(null, sName); }
	
	private Stadium(Integer iId, String sName) {
		setName(sName);
		_iId = iId;
	}
	
	public String toString() { return super.toString() + ":" + _iId + ":" + _sName; }
	
	//Precondición: El identificador no debe ser nulo
	public static Stadium Get(int iId) throws IOException, SQLException {
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Database.Connection();
			rs = con.createStatement().executeQuery("SELECT name FROM stadium WHERE id = " + iId);
			
			if (rs.next()) 
				return new Stadium(iId, rs.getString("name"));
			return null;
			
		}
		catch (SQLException e) { throw e; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}

	}
	
	public void Save() throws IOException, SQLException{
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Database.Connection();
			if (_iId != null) 
				con.createStatement().executeUpdate("UPDATE stadium SET name=" + Database.String2Sql(_sName, true, false) + " WHERE id=" + _iId);
			else {
				con.createStatement().executeUpdate("INSERT INTO stadium (name) VALUES (" + Database.String2Sql(_sName, true, false) + ")");
				_iId = Database.LastId(con);
			}
		}
		catch (SQLException e) { throw e; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	public void Delete() throws IOException, SQLException{
		if(_iId == null || _dtDeletedAt != null)
			throw new IllegalStateException("El elemento ha sido borrado previamente, o no se encunetra en la base de datos.");
		
		Connection con = null;
		try {
			con = Database.Connection();

			con.createStatement().executeUpdate("DELETE FROM stadium WHERE id=" + _iId);
			_dtDeletedAt = new Date();
		}
		catch (SQLException e) { throw e; }
		finally {
			if (con != null) con.close();
		}

	}
	
	private static String Where(String sName) {
		if(sName != null)
			return "WHERE name LIKE " + Database.String2Sql(sName, true, true);
		return "";
	}
	
	public static ArrayList<Stadium> Search(String sName) throws IOException, SQLException{
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Database.Connection();
			ArrayList<Stadium> aEstadio = new ArrayList<Stadium>();
			
			rs = con.createStatement().executeQuery("SELECT id, name FROM stadium " + Where(sName) + " ORDER BY name ASC");
			while(rs.next()) aEstadio.add(new Stadium(rs.getInt("id"), rs.getString("name")));
			
			return aEstadio;
		}
		catch (SQLException e) { throw e; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
}
