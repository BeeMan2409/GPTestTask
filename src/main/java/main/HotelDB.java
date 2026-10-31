package main;

import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelDB extends JpaRepository<Hotel, Integer> {

    @EntityGraph(attributePaths = "amenities")
    Hotel findWithAmenitiesById(Integer id);

    public Hotel findByCountry(String country);

    public Hotel findByCity(String city);

    public Hotel findByBrand(String brand);

    public Hotel findByName(String name);

    @EntityGraph(attributePaths = "amenities")
    public Hotel findById(int id);

    public void deleteByName(String name);

}
