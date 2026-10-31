package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    @Transactional
    public List<Hotel> findByAmeneties(String am){
        List<Hotel> hotels = hotelDB.findAll();
        ArrayList<Hotel> hotels1 = new ArrayList<>();
        for (Hotel el: hotels){
            for (String amen: el.getAmenities()){
                if (am.equals(amen)){
                    hotels1.add(el);
                }
            }
        }
        hotels = hotels1;
        return hotels;
    }

    @Transactional
    public Hotel findByCountry(String c){
        return hotelDB.findByCountry(c);
    }

    @Transactional
    public Hotel findByCity(String c){
        return hotelDB.findByCity(c);
    }

    @Transactional
    public Hotel findByBrand(String brand){
        return hotelDB.findByBrand(brand);
    }

    @Transactional
    public Hotel findByName(String name){
        return hotelDB.findByName(name);
    }

    public HotelDto.Short toShort(Hotel h) {
        // address в краткой форме — одна строка, склеиваем из полей
        String addr = h.getHouseNum() + " " + h.getStreet() + ", "
                + h.getCity() + ", " + h.getPostCode() + ", " + h.getCountry();
        return new HotelDto.Short(h.getId(), h.getName(), h.getDescription(), addr, h.getPhone());
    }

    public HotelDto.Full toFull(Hotel h) {
        HotelDto.Full dto = new HotelDto.Full();
        dto.setId(h.getId());
        dto.setName(h.getName());
        dto.setDescription(h.getDescription());
        dto.setBrand(h.getBrand());

        dto.setAddress(new HotelDto.Full.Address(
                Integer.parseInt(h.getHouseNum()), // или сделайте houseNum int в Hotel
                h.getStreet(), h.getCity(), h.getCountry(), h.getPostCode()));

        dto.setContacts(new HotelDto.Full.Contacts(h.getPhone(), h.getEmail()));

        int[] at = h.getArivalTime();
        dto.setArrivalTime(new HotelDto.Full.ArrivalTime(
                String.format("%02d:00", at[0]),
                String.format("%02d:00", at[1])));

        dto.setAmenities(h.getAmenities());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<Hotel> search(String name, String brand, String city, String country) {
        return hotelDB.findAll().stream()
                .filter(h -> name == null || name.isBlank()
                        || h.getName().equalsIgnoreCase(name))
                .filter(h -> brand == null || brand.isBlank()
                        || h.getBrand().equalsIgnoreCase(brand))
                .filter(h -> city == null || city.isBlank()
                        || h.getCity().equalsIgnoreCase(city))
                .filter(h -> country == null || country.isBlank()
                        || h.getCountry().equalsIgnoreCase(country))
                .toList();
    }
}
