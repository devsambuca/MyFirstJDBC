package com.dev.app.dao;

import com.dev.app.ApplicationJDBC;
import com.dev.app.model.Skill;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOSkill implements GenericDAO<Skill> {
    @Override
    public void create(Skill skill) {
        String sql = "INSERT INTO skills value (?,?)";
        try(Connection connection = ApplicationJDBC.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, skill.getId());
            preparedStatement.setString(2, skill.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill read(long id) {
        String sql = "SELECT * FROM skills WHERE id = ?";
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
    public void update(Skill skill) {
        String sql = "UPDATE skills SET name =?, cost =? WHERE id=?";
        try(Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, skill.getId());
            preparedStatement.setString(2, skill.getName());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing skill was updated successfully!");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM skills WHERE id =?";
        try(Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A skill was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Skill> getAll() {
        String sql = "SELECT * FROM skills";
        ArrayList<Skill> skills = new ArrayList<>();
        try(Connection connection = ApplicationJDBC.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                skills.add(new Skill(resultSet.getLong("id"),resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }
}

