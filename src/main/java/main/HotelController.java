package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @DeleteMapping("/property-view/hotels/remove/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String remove(@PathVariable Integer id){
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

    @PostMapping("/property-view/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel create(@RequestBody HotelDto.HotelCreateRequest request) {
        return hotelManager.create(request);
    }

    @GetMapping("/property-view/hotels/histogram/{param}")
    public List<String> histogram(@PathVariable String param) {
        ArrayList<String> hist = new ArrayList<>();
        String repeat = null;
        int count = 0;
        String s = "";
        for (String el : hotelManager.histogram(param)) {
            for (String el2 : hotelManager.histogram(param)) {
                if (el.equals(el2)) {
                    count++;
                }
            }
            if (!hist.contains(el + ": " + count)) {
                hist.add(el + ": " + count);
            }
            count = 0;
        }
        return hist;
    }

    @GetMapping("/property-view/hotels/histogram/amenities")
    public Map<String, Long> amenitiesHistogram() {
        return hotelManager.amenitiesHistogram();
    }

    @PostMapping("/property-view/hotels/{id}/amenities")
    @ResponseStatus(HttpStatus.OK)
    public HotelDto.Full setAmenities(@PathVariable Integer id,@RequestBody List<String> am){
        hotelManager.setAmenities(id,am);
        return hotelManager.toFull(hotelManager.searchById(id));
    }
}
