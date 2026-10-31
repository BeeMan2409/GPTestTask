package main;

import org.springframework.stereotype.Service;

@Service
public class HotelCreator {

    private String name;
    private String city;
    private String address;
    private int stars;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }


}
