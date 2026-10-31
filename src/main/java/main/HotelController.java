package main;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelManager hotelManager;

    @GetMapping("/property-view/hotels/test")
    public Hotel getInfo(@RequestParam Integer id){
        return hotelManager.searchById(id);
    }

    @GetMapping("/property-view/hotels/add")
    public String add(@RequestParam String name){
        hotelManager.add(name);
        Hotel hotel = hotelManager.findByName(name);
        return "add hotel " + hotel.getId() + " " + hotel.getName();
    }

    @GetMapping("/property-view/hotels/remove")
    public String remove(@RequestParam Integer id){
        Hotel hotel = hotelManager.searchById(id);
        hotelManager.removeById(id);
        return "completed remove " + hotel.getName();
    }

    @GetMapping("/property-view/hotels")
    public List<HotelDto.Short> getAll() {
        return hotelManager.getAll().stream().map(hotelManager::toShort).toList();
    }

    @GetMapping("/property-view/hotels/{id}")
    public HotelDto.Full getById(@PathVariable Integer id) {
        return hotelManager.toFull(hotelManager.searchById(id));
    }

    @GetMapping("/property-view/hotels/search")
    public List<HotelDto.Short> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenity){
        if (amenity == null) {
            return hotelManager.search(name, brand, city, country)
                    .stream()
                    .map(hotelManager::toShort)
                    .toList();
        } else {
            return hotelManager.findByAmeneties(amenity).stream().map(hotelManager::toShort).toList();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponse create(@RequestBody HotelCreateRequest request) {
        return hotelService.create(request);
    }
}
