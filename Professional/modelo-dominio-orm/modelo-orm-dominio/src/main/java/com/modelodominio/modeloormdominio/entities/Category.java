package com.modelodominio.modeloormdominio.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(columnDefinition = "TEXT")
    String description;

    @OneToMany(mappedBy = "category")
    private List<Activity> activities = new ArrayList<>();

    public Category() {    }
    public Category(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void addActivity(Activity activity) {
        activities.add(activity);
    }
    public List<Activity> getActivities() {
        return activities;
    }
}
