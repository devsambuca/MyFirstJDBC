package com.dev.app.dao;

import com.dev.app.ApplicationJDBC;
import com.dev.app.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCustomer implements GenericDAO<Customer> {

    public void addCustomerProjetcs(long proj_id, long cust_id) {
        String sql = "INSERT  INTO projects_customers (proj_id, cust_id) " +
                "SELECT p.id, c.id " +
                "FROM projects p, customers c " +
                "WHERE p.id = ? " +
                "AND c.id = ?";
        try (Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, proj_id);
            preparedStatement.setLong(2, cust_id);
            int rowsAdded = preparedStatement.executeUpdate();
            if (rowsAdded > 0) {
                System.out.println("A project was added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Customer customer) {
        String sql = "INSERT INTO customers value (?,?)";
        try(Connection connection = ApplicationJDBC.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer read(long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (Connection connection = ApplicationJDBC.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                System.out.println("id №"+ id +": " + name + " " + address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customers SET name =?, cost =? WHERE id=?";
        try(Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing customer was updated successfully!");}
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM customers WHERE id =?";
        try(Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A customer was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM customers";
        ArrayList<Customer> customers = new ArrayList<>();
        try(Connection connection = ApplicationJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                customers.add(new Customer(resultSet.getLong("id"),resultSet.getString("name"),resultSet.getString("address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
