package main;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String phone;
    private String adress;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Hotel(){}

    public Hotel(String name, String adress, String phone, String description){
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.description = description;
    }

}
