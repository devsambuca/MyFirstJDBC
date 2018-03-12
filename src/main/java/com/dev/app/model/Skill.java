package com.dev.app.model;

import java.util.Set;

public class Skill {

    private long id;
    private String name;
    private Set<Developer>developers;

    public Skill() {
    }

    public Skill(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Skill(long id, String name, Set<Developer> developers) {
        this.id = id;
        this.name = name;
        this.developers = developers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developers=" + developers +
                '}';
    }
}
