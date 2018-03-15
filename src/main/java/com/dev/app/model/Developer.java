package com.dev.app.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by z-FominykhVJ on 01.03.2018.
 */
public class Developer {

    private long id;
    private String fullName;
    private int salary;
    private Set<Skill> skills;

    public Developer() {
    }

    public Developer(long id, String fullName, int salary) {
        this.id = id;
        this.fullName = fullName;
        this.salary = salary;
    }

    public Developer(long id, Set<Skill> skills) {
        this.id = id;
        this.skills = skills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", salary=" + salary +
                '}';
    }
}

