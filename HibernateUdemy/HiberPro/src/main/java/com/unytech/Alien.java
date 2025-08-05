package com.unytech;

import jakarta.persistence.*;

import java.util.List;

/** created a new Alien class to check the modifications of
 * the table names and column names and to execute the embedding and oneToOne,
 * OneToMany, ManyToOne, ManyToMany mapping */
@Entity
//@Table(name="alien_table")
public class Alien {
    @Id
    private int aid;
 //   @Column(name="alien_name")
    private String aname;

//    @Transient
    /** if we use @Transient that variable wont save
     in database but exists in code while performing the certain operation */
    private String tech;
//    @OneToOne
//    @ManyToMany
    @OneToMany(fetch=FetchType.EAGER)
    private List<Laptop> laptops;


    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", tech='" + tech + '\'' +
                ", laptop='" + laptops + '\'' +
                '}';
    }
}
