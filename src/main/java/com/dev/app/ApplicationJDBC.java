package com.dev.app;

import com.dev.app.dao.DAOCompany;
import com.dev.app.dao.DAOCustomer;
import com.dev.app.dao.DAODeveloper;
import com.dev.app.dao.DAOProject;
import com.dev.app.model.Developer;

import java.sql.*;

public class ApplicationJDBC {

    public static void main(String[] args) {
        DAODeveloper daoDeveloper = new DAODeveloper();
        DAOCompany daoCompany = new DAOCompany();
        DAOCustomer daoCustomer = new DAOCustomer();
        DAOProject daoProject = new DAOProject();
        daoDeveloper.read(1);
        daoDeveloper.addDevSkills(1, 5);
        daoCompany.addCompanyProjects(1, 2);
        daoCustomer.addCustomerProjetcs(1, 2);
        daoProject.addProjectsDevelopers(1, 3);
        daoDeveloper.create(new Developer(11, "Fedotova Anna", 350000));

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