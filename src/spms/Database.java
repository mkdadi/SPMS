package spms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	public String host;
	public int port;
	public String user;
	public String pass;
	public String name;
	
	public Database()
	{}
	
	public Connection connect()
	{
		Connection conn = null;
		try {
			Properties connectionProps = new Properties();
			connectionProps.put("user", Spms.dbUser);
			connectionProps.put("password", Spms.dbPassword);
			conn = DriverManager.getConnection("jdbc:mysql://"
					+ Spms.dbHost + ":" + Spms.dbPort + "/" + Spms.dbName,
					connectionProps);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		return conn;
	}
	
	public boolean exec(String cmd)
	{
		Connection conn=this.connect();
		if(conn==null)return false;
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
}
