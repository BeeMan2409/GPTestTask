package main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelDB extends JpaRepository<Hotel, Integer> {

    public Hotel findByName(String name);

    public void deleteByName(String name);

}
