
package com.retail.springservice.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {
 private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
             /*Properties prop = new Properties();
                InputStream inputStream = DBUtility.class.getClassLoader().getResourceAsStream("properties/config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");*/
            	String DRIVER="oracle.jdbc.driver.OracleDriver";  
            	String CONNECTION_URL="jdbc:oracle:thin:@localhost:1521:xe";  
            	String USERNAME="QMS";  
            	String PASSWORD="1234";  
                Class.forName(DRIVER);
                
                connection = DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            } /*catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return connection;
        }

    }

}