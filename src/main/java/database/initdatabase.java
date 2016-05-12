package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class initdatabase {
	public void initialize() {

		final String DB_URL = "jdbc:mysql://localhost:3306/test";
		final String USER = "root";
		final String PASS = "123456";
		final String newDB_URL = "jdbc:mysql://localhost:3306/webmagic" +"?user="+USER+"&password="+PASS+"&useUnicode=true&characterEncoding=utf-8";
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "create database if not exists webmagic;";
			stmt.execute(sql);
			conn = DriverManager.getConnection(newDB_URL, USER, PASS);
			stmt = conn.createStatement();
			sql =   //"set names \'utf8\';"
					//"SET character_set_client = utf8 ;"
					//+ "SET character_set_connection = utf8 ;   "
					//+ "SET character_set_database = utf8 ;"
					//+ "SET character_set_results = utf8 ;"
					//+ "SET character_set_server = utf8 ; "
					"create table if not exists webpage(id int(11) NOT NULL AUTO_INCREMENT,baseUrl varchar(255) NOT NULL,"
					+ "content text,savetime date DEFAULT NULL,title varchar(255) NOT NULL,author varchar(255) DEFAULT NULL,type varchar(20) DEFAULT NULL,other varchar(20) DEFAULT NULL,"
					+ "PRIMARY KEY (id)) ENGINE=InnoDB   DEFAULT   CHARSET=utf8;";
			stmt.execute(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception exc) {
			// Handle errors for Class.forName
			exc.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}