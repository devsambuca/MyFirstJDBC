package com.dev.app.dao;

import com.dev.app.ApplicationJDBC;
import com.dev.app.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOProject implements GenericDAO<Project> {

    @Override
    public void create(Project project) {
        String sql = "INSERT INTO projects value (?,?,?)";
        try(Connection connection = ApplicationJDBC.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, project.getId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setInt(3, project.getCost());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project read(long id) {
        String sql = "SELECT * FROM projects WHERE id = ?";
        try (Connection connection = ApplicationJDBC.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int cost = resultSet.getInt("cost");
                System.out.println("id №"+ id +": " + name + " " + cost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Project project) {
        String sql = "UPDATE projects SET name =?, cost =? WHERE id=?";
        try(Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, project.getId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setInt(3, project.getCost());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing project was updated successfully!");}
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(long id) {
    String sql = "DELETE FROM projects WHERE id =?";
    try(Connection connection = ApplicationJDBC.getConnection()) {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A project was deleted successfully!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public List<Project> getAll() {
        String sql = "SELECT * FROM projects";
        ArrayList<Project> projects = new ArrayList<>();
        try(Connection connection = ApplicationJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                projects.add(new Project(resultSet.getLong("id"),resultSet.getString("name"),resultSet.getInt("cost")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
