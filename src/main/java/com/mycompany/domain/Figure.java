package com.mycompany.domain;

import javax.persistence.*;

@Entity
public class Figure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double attackPower;
    private Double abilityPower;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Figure() {
    }

    public Figure(Double attackPower, Double abilityPower, String name) {
        this.attackPower = attackPower;
        this.abilityPower = abilityPower;
        this.name = name;
    }

    public Figure(Double attackPower, Double abilityPower, String name, User user) {
        this.attackPower = attackPower;
        this.abilityPower = abilityPower;
        this.name = name;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(Double attackPower) {
        this.attackPower = attackPower;
    }

    public Double getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(Double abilityPower) {
        this.abilityPower = abilityPower;
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
