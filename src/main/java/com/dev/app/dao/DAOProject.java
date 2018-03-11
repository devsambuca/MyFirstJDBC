package com.dev.app.dao;

import com.dev.app.ApplicationJDBC;
import com.dev.app.model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOProject implements GenericDAO<Project> {

    @Override
    public void create(Project project) {
        String sql = "INSERT INTO project value (?,?)";
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
        String sql = "SELECT * FROM project WHERE id = ?";
        try (Connection connection = ApplicationJDBC.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int cost = resultSet.getInt("cost");
                System.out.println("id № " + id + name + " " + cost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Project project) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Project> getAll() {
        return null;
    }
}
