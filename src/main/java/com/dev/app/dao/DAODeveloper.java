package com.dev.app.dao;

import com.dev.app.ApplicationJDBC;
import com.dev.app.model.Developer;
import com.dev.app.model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DAODeveloper implements GenericDAO<Developer> {

    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rs;

    public void addSkill(long dev_id, long sk_id) {
        String sql = "SELECT id FROM skills";
        try {
            Skill skill =  new Skill();
            rs = pstmt.executeQuery(sql);
            pstmt.setLong(1, skill.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void create(Developer developer) {
        String sql = "INSERT INTO developers values (?,?,?)";
        try (Connection connection = ApplicationJDBC.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, developer.getId());
            ps.setString(2, developer.getFullName());
            ps.setLong(3, developer.getSalary());
            ps.execute();
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
                System.out.println("id №"+ id + ": " + fullName + " " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Developer developer) {
        String sql = "UPDATE developers SET name =? ,salary =? WHERE id =?";
        try(Connection connection = ApplicationJDBC.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, developer.getFullName());
            preparedStatement.setInt(2, developer.getSalary());
            preparedStatement.setLong(3, developer.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing developer was updated successfully!");}
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
        String sql = "SELECT * FROM developers INNER JOIN skills_developers ON developers.id = skills_developers.dev_id " +
                "INNER JOIN skills ON skills.id = skills_developers.sk_id";
        ArrayList<Developer> developers = new ArrayList<>();
        try(Connection connection = ApplicationJDBC.getConnection()) {
            Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(sql);
              while (resultSet.next()) {
                  developers.add(new Developer(resultSet.getLong("id"),resultSet.getString("name"),resultSet.getInt("salary")));
              }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developers;
    }
}

