package main;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelManager hotelManager;

    @GetMapping("/property-view/hotels")
    public String getAll(){
        String hotels = "";
        if (!hotelManager.getAll().isEmpty()) {
            for (Hotel el : hotelManager.getAll()) {
                hotels = hotels + "<br>   {<br>      id: " + el.getId() + "<br>      name: " + el.getName() + "<br>      descr: " + el.getDescription() + "<br>      adress: " + el.getAdress() + "<br>      phone number: " + el.getPhone() + "<br>   }<br>";
            }
            return "<div style=\"white-space: pre-wrap;\">[<br>" + hotels + "<br>]</div>";
        } else {
            return "Никаких отелей тут нет";
        }
    }

    @GetMapping("/property-view/hotels/search")
    public String search(@RequestParam String name){
        Hotel hotel = hotelManager.search(name);
        return hotel.getName();
    }

    @GetMapping("/property-view/hotels/test")
    public Hotel getInfo(@RequestParam Integer id){
        return hotelManager.searchById(id);
    }

    @GetMapping("/property-view/hotels/searchid")
    public String searchID(@RequestParam Integer id){
        Hotel hotel = hotelManager.searchById(id);
        List<String> am = hotel.getAmenities();
        String amenities = "";
        if (!hotel.getAmenities().isEmpty()) {
            for (String el : am) {
                amenities = amenities + "         " + el + "<br>";
            }
        } else {
            amenities = "   none";
        }
        return "<div style=\"white-space: pre-wrap;\">{<br>" + "id: "
                + hotel.getId() + "<br>   name: "
                + hotel.getName() + "<br>   desc: "
                + hotel.getDescription() + "<br>   brand: "
                + hotel.getBrand() + "<br>      adress:<br>   {<br>         houseNumber: "
                + hotel.getHouseNum() + "<br>         street:"
                + hotel.getStreet()  + "<br>         country: "
                + hotel.getCountry() + "<br>         city: "
                + hotel.getCity() + "<br>         postCode: "
                + hotel.getPostCode() + "<br>   }<br>      contacts<br>   {<br>         phone: "
                + hotel.getPhone() + "<br>         email: "
                + hotel.getEmail() + "<br>}<br>      arivalTime:<br>   {<br>         checkIn: "
                + hotel.getArivalTime()[0] + "<br>         checkOut: "
                + hotel.getArivalTime()[1] + "<br>   }<br>      amenities: <br>      [<br>"
                + amenities + "      ]"
                + "<br>}</div>";
    }

    @GetMapping("/property-view/hotel/add")
    public String add(@RequestParam String name){
        hotelManager.add(name);
        Hotel hotel = hotelManager.search(name);
        return "add hotel " + hotel.getId() + " " + hotel.getName();
    }

    @GetMapping("/property-view/hotels/remove")
    public String remove(@RequestParam Integer id){
        Hotel hotel = hotelManager.searchById(id);
        hotelManager.removeById(id);
        return "completed remove " + hotel.getName();
    }

}
