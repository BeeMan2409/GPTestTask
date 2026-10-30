package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManager {

    @Autowired
    private HotelDB hotelDB;

    public HotelDB getHotelDB() {
        return hotelDB;
    }

    public List<Hotel> getAll(){
        return hotelDB.findAll();
    }

    public Hotel search(String name){
        return hotelDB.findByName(name);
    }

    public void add(String name){
        Hotel hotel = new Hotel(name, "-", "-", "-");
        hotelDB.save(hotel);
    }

    public void remove(String name){
        hotelDB.deleteByName(name);
    }
}
