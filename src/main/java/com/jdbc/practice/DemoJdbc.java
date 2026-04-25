package com.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoJdbc {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		/*
		 * 1. Import Packages
		 * 2. Load Driver
		 * 3. Register Driver
		 * 4. Create Connection
		 * 5. Create Statement
		 * 6. Execute Statement
		 * 7. Close Connection
		 */
		
		String url;
		String username;
		String password;
		
		url="jdbc:postgresql://localhost:5432/products";    // "jdbc:postgresql://network_ip_address:port_number/database_name"  
		username="postgres";
		password="mypass";
		
		String sql1 = "select * from product where productid=110";
		
		Class.forName("org.postgresql.Driver");
		Connection con =DriverManager.getConnection(url,username,password);
		
		
		
		System.out.println("Connection Established");
		
		Statement st=con.createStatement();
		
		String sql2= "insert into product values (105, 'Book', 200)";
		String sql3= "update product set name = 'ClassMate Book' where productid=105";
		String sql4= "delete from product where productid=105";
		
		String sql5= "insert into product values (?,?,?)";
		
		int pid1=112;
		String pname1="Pencil";
		int pprice=15;
		
		boolean status1 =st.execute(sql3);   // it returns false if the query is an insert
		
		PreparedStatement st1= con.prepareStatement(sql5);
		
		st1.setInt(1, pid1);
		st1.setString(2, pname1);
		st1.setInt(3, pprice);
		
		// st1.execute(); 
		
		ResultSet rs= st.executeQuery(sql1);   //used for Select queries
		while(rs.next()) {
			  int pId=rs.getInt("productid");
		      String nameS= rs.getString("name");
		      int priceP=rs.getInt("price");
		      
		      System.out.println("Product id is "+pId+" Name is "+nameS+" price is "+priceP);
		}
		con.close();
		
		System.out.println("Connection Closed");

	}
}