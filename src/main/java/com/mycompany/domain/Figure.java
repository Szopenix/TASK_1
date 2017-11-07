package com.mycompany.domain;

import javax.persistence.*;

@Entity
public class Figure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double power;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Figure() {
    }

    public Figure(Double power, String name) {
        this.power = power;
        this.name = name;
    }

    public Figure(Double power, String name, User user) {
        this.power = power;
        this.name = name;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
