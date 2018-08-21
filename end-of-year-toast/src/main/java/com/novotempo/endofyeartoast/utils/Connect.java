package com.novotempo.endofyeartoast.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connect {
	
	private Connection conn = null;
	private static Properties properties;
	
	public Connect(){
		loadProperties();
	}
	
	static {
		loadProperties();
	}
	
	public void release (Connection conn){
		try {
			if (conn != null) 
				conn.close(); 
		} catch(Exception e) { 
			e.printStackTrace();
		}
	}
	
	public static Properties loadProperties() {
		
		InputStream in = null; 
		
        properties = new Properties();
        try {
        	in = Connect.class.getClassLoader().getResourceAsStream("application.properties");
        	properties.load(in);
        } catch (Exception ex) {
           ex.printStackTrace();
        } finally {
        	if(in != null) {
        		try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }
        
        return properties;
	}
	
	public Connection getConnection(String dbName, boolean isOfficial) {
		dbName = isOfficial ? dbName : dbName + "_Teste";
		
        String driver 		 = properties.getProperty( dbName + ".driver" );
        String connectString = properties.getProperty( dbName + ".url" ); 
        String user 		 = properties.getProperty( dbName + ".user" );
        String password 	 = properties.getProperty( dbName + ".password" );
        try {
            Class.forName(driver);
            
            if(conn == null) 
            	conn = DriverManager.getConnection(connectString, user, password);
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
        return conn;
	}
}
