package com.assignment3.utils;

import java.sql.*;

public class JDBCOps {
    static final String db_url= "jdbc:mysql://localhost:3306/MY_DB";
    static final String user = "root";
    static final String pass = "wulin9786";
    public static Connection jdbcConnection() throws SQLException {
        return DriverManager.getConnection(db_url, user, pass);
    }
}
