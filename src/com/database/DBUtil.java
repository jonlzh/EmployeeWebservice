package com.database;

import java.sql.*;

public class DBUtil {

    //local db
    static final String DATABASE_USERNAME = "root";
    static final String DATABASE_PASSWORD = "root";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";

    static Connection con = null;
    static {
        try {
//			Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DATABASE_URL,
                    DATABASE_USERNAME, DATABASE_PASSWORD);
            System.out.println("Connection Established!");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return con;
    }
}
