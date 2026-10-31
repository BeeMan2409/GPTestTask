package main;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int[] arivalTime = {0, 24};
    private String name;
    private String description;
    private String phone;
    private String adress;
    private String email;
    private String country;
    private String city;
    private String postCode;
    private String houseNum;
    private String street;
    private String brand;
    private List<String> amenities = new ArrayList<>();

    public int[] getArivalTime() {
        return arivalTime;
    }

    public String getBrand(){
        return brand;
    }

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

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
        amenities.add("-");
    }

}
