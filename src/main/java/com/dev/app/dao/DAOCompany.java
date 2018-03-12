package com.dev.app.dao;

import com.dev.app.ApplicationJDBC;
import com.dev.app.model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCompany implements GenericDAO<Company>{
    @Override
    public void create(Company company) {
        String sql = "INSERT INTO companies value (?,?)";
        try(Connection connection = ApplicationJDBC.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, company.getId());
            preparedStatement.setString(2, company.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Company read(long id) {
        String sql = "SELECT * FROM companies WHERE id = ?";
        try (Connection connection = ApplicationJDBC.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                System.out.println("id №"+ id +": " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Company company) {
        String sql = "UPDATE companies SET name =?, cost =? WHERE id=?";
        try(Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, company.getId());
            preparedStatement.setString(2, company.getName());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing company was updated successfully!");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM companies WHERE id =?";
        try(Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A company was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Company> getAll() {
        String sql = "SELECT * FROM companies";
        ArrayList<Company> companies = new ArrayList<>();
        try(Connection connection = ApplicationJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                companies.add(new Company(resultSet.getLong("id"),resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
