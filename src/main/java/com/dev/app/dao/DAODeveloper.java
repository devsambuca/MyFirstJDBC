package com.dev.app.dao;

import com.dev.app.model.Developer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by z-FominykhVJ on 05.03.2018.
 */
public class DAODeveloper implements GenericDAO<Developer> {

    public void create(Developer developer) {

        String sql = "INSERT INTO developers values (?,?,?)";
        Connection connection = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, developer.getId());
            preparedStatement.setString(2, developer.getFullName());
            preparedStatement.setLong(3, developer.getSalary());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public Developer read(long id) {
        return null;
    }

    public void update(Developer developer) {

    }

    public void delete(long id) {

    }

    public List<Developer> getAll() {
        return null;
    }
}
