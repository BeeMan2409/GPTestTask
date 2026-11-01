package main;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "arival_time", columnDefinition = "varchar array")
    private String[] arivalTime = {"0", "23"};
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
    @ElementCollection
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private List<String> amenities = new ArrayList<>();

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setArivalTime(String[] arivalTime) {
        this.arivalTime = arivalTime;
    }

    @Transactional
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String[] getArivalTime() {
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
        this.houseNum = "0";
        amenities.add("-");
    }

}
