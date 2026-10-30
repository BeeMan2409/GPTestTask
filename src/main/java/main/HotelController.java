package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    @Autowired
    HotelManager hotelManager;

    @GetMapping("/api/hotel/getAll")
    public String getAll(){
        String hotels = "";
        if (!hotelManager.getAll().isEmpty()) {
            for (Hotel el : hotelManager.getAll()) {
                hotels = hotels + el.getId() + " " + el.getName() + " ";
            }
            return hotels;
        } else {
            return "Никаких отелей тут нет";
        }
    }

    @GetMapping("/api/hotel/search")
    public String search(@RequestParam String name){
        Hotel hotel = hotelManager.search(name);
        return hotel.getName();
    }

    @GetMapping("/api/hotel/add")
    public String add(@RequestParam String name){
        hotelManager.add(name);
        Hotel hotel = hotelManager.search(name);
        return "add hotel " + hotel.getId() + " " + hotel.getName();
    }

}
