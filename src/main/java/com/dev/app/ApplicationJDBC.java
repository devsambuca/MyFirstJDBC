package com.dev.app;

import com.dev.app.dao.DAODeveloper;
import com.dev.app.dao.DAOProject;
import com.dev.app.model.Developer;
import com.dev.app.model.Project;
import com.dev.app.model.Skill;

import java.sql.*;
import java.util.List;
import java.util.Set;

public class ApplicationJDBC {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DAODeveloper daoDeveloper = new DAODeveloper();
            daoDeveloper.read(1);
            daoDeveloper.addDevSkills(1,5);
//            daoDeveloper.create(new Developer(11,"Fedotova Anna", 350000));
    }

    public static Connection getConnection() {
        try {

            final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            final String DATABASE_URL = "jdbc:mysql://localhost/mytestdb";
            final String USER = "root";
            final String PASSWORD = "root";
            Class.forName(JDBC_DRIVER);

         System.out.println("Checking connection to MySQL");

            Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            if (null != connection) {
                System.out.println("Connection established");
            } else {
                System.out.println("Connection not established");
            }
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}