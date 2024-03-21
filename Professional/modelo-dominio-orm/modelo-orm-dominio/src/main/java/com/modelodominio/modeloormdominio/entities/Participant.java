package com.modelodominio.modeloormdominio.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String name;
    String email;

    @ManyToMany(mappedBy = "participants")
    private Set<Activity> activities = new HashSet<>();


    public Participant() {    }
    public Participant(Long id, String name, String email) {
        Id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity){
         activities.add(activity);
    }
}
