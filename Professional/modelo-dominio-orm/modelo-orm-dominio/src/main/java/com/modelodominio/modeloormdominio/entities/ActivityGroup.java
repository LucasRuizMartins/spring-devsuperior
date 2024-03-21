package com.modelodominio.modeloormdominio.entities;


import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_activity_group")
public class ActivityGroup {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant start;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant endActivity;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public  ActivityGroup(){}

    public ActivityGroup(long id, Instant start, Instant endActivity, Activity activity) {
        this.id = id;
        this.start = start;
        this.endActivity = endActivity;
        this.activity = activity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEndActivity() {
        return endActivity;
    }

    public void setEndActivity(Instant endActivity) {
        this.endActivity = endActivity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}