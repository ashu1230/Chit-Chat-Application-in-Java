package com.ashu.chatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ashu.chatapp.dto.UserDTO;
import com.ashu.chatapp.utils.Encryption;

//	user CRUD perform operation 
public class UserDAO {
	
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL = "select userid from users where Userid=? and password=?";
		try {
			con=CommonDao.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedpsw = Encryption.passwordEncrypt(new String (userDTO.getPassword()));
			pstmt.setString(2, encryptedpsw);
			rs = pstmt.executeQuery();
			return rs.next();
//			if(rs.next()) {
//				return true;
//			}
//			else {
//				return false;
//			}
		}finally {
			if(rs!= null) {
				rs.close();
			}
			if(pstmt !=null) { 
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
//	public int add(String userid, String password, byte age, String city, String phone,String email, String country, String areacode, String stdcode){
		public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException , Exception{
			System.out.println("reco "+userDTO.getUserid()+ " pass "+ userDTO.getPassword());
			Connection connection = null;
			Statement stmt = null;
			try { // garded resion
//			1. connection create.
			connection = CommonDao.createConnection();
//			2. we do a query
			stmt = connection.createStatement();
//			insert into users (userid, password) values('ashu','ashu1230');
			int record = stmt.executeUpdate("insert into users (userid, password) values('"+userDTO.getUserid()+"', '"+Encryption.passwordEncrypt( new String (userDTO.getPassword()))+"')");// insert, delete, update
			return record;
			} finally { //always executed(Resource Clean)
			if(stmt != null) {
			stmt.close();
			}
			if (connection != null) {
			connection.close();
			}
		}
				
	}
}
