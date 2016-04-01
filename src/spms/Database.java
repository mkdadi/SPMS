package spms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	public String host;
	public int port;
	public String user;
	public String pass;
	public String name;
	private Connection conn;
	
	public Database()
	{
		this.connect();
	}
	
	public Connection connect()
	{
		try {
			Properties connectionProps = new Properties();
			connectionProps.put("user", Spms.dbUser);
			connectionProps.put("password", Spms.dbPassword);
			this.conn = DriverManager.getConnection("jdbc:mysql://"
					+ Spms.dbHost + ":" + Spms.dbPort + "/" + Spms.dbName,
					connectionProps);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		return this.conn;
	}
	
	public boolean Update(String cmd)
	{
		if(this.conn==null)return false;
		Statement st=null;
		try {
			st = conn.createStatement();
			st.executeUpdate(cmd);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ResultSet Query(String cmd)
	{
		if(this.conn==null)return null;
		Statement st=null;
		ResultSet rs=null;
		try {
			st = conn.createStatement();
			rs=st.executeQuery(cmd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	
	public boolean disconnect()
	{
		try {
			if(!this.conn.isClosed())
				this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
