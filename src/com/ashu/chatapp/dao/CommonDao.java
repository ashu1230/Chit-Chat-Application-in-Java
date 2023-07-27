package com.ashu.chatapp.dao;

import java.sql.Connection;
import static com.ashu.chatapp.utils.ConfigReader.getValue;	
import java.sql.DriverManager;
import java.sql.SQLException;

public interface CommonDao {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
//		step-1 load driver 
		Class.forName(getValue("DRIVER"));
//		step 2 making a connection.
		final String CONNECTION_STRING = getValue("CONNECTION_ULR");
		String USER_ID = getValue("USERID");
		String PASSWORD = getValue("PASSWORD");
		Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_ID, PASSWORD);
		if(con != null) {
			System.out.println("Connection Created...");
			//con.close();
		}
		return con;
	}
	
}
