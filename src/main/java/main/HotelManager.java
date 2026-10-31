package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelManager {

    @Autowired
    private HotelDB hotelDB;

    public HotelDB getHotelDB() {
        return hotelDB;
    }

    @Transactional
    public List<Hotel> getAll(){
        return hotelDB.findAll();
    }

    @Transactional
    public Hotel search(String name){
        return hotelDB.findByName(name);
    }

    @Transactional
    public Hotel searchById(Integer id){
        return hotelDB.findWithAmenitiesById(id);
    }

    @Transactional
    public void add(String name){
        Hotel hotel = new Hotel(name, "-", "-", "-");
        hotelDB.save(hotel);
    }

    @Transactional
    public void remove(String name){
        hotelDB.deleteByName(name);
    }

    @Transactional
    public void removeById(Integer id){
        hotelDB.deleteById(id);
    }
}
