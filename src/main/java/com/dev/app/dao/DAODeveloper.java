package com.dev.app.dao;

import com.dev.app.ApplicationJDBC;
import com.dev.app.model.Developer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAODeveloper implements GenericDAO<Developer> {

    public void addDevSkills(long dev_id, long sk_id) {
        String sql = "INSERT  INTO developer_skills (dev_id, sk_id) " +
                "SELECT d.id, s.id " +
                "FROM developers d, skills s " +
                "WHERE d.id = ? " +
                "AND s.id = ?";
        try (Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, dev_id);
            preparedStatement.setLong(2, sk_id);
            int rowsAdded = preparedStatement.executeUpdate();
            if (rowsAdded > 0) {
                System.out.println("A skills was added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Developer developer) {
        String sql1 = "INSERT INTO developers VALUES (?,?,?)";
        Connection connection = ApplicationJDBC.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setLong(1, developer.getId());
            preparedStatement.setString(2, developer.getFullName());
            preparedStatement.setLong(3, developer.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Developer read(long id) {
        String sql = "SELECT * FROM developers WHERE id = ?";
        try (Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                String fullName = resultSet.getString("name");
                int salary = resultSet.getInt("salary");
                System.out.println("id №" + id + ": " + fullName + " " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Developer developer) {
        String sql = "UPDATE developers SET name =? ,salary =? WHERE id =?";
        try (Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, developer.getFullName());
            preparedStatement.setInt(2, developer.getSalary());
            preparedStatement.setLong(3, developer.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing developer was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM developers WHERE id = ?";
        try (Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A developer was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Developer> getAll() {
        String sql = "SELECT * FROM developers";
        ArrayList<Developer> developers = new ArrayList<>();
        try (Connection connection = ApplicationJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                developers.add(new Developer(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("salary")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }
}

