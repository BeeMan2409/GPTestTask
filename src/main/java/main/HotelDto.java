package main;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelDto {

    // ===== Краткая информация (GET /hotels, /search, ответ POST /hotels) =====
    public static class Short {
        private int id;
        private String name;
        private String description;
        private String address;   // одна строка, как в ТЗ
        private String phone;

        public Short() {}

        public Short(int id, String name, String description, String address, String phone) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.address = address;
            this.phone = phone;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
    }

    // ===== Расширенная информация (GET /hotels/{id}) =====
    @JsonPropertyOrder({"id", "name", "description", "brand", "address", "contacts", "arrivalTime", "amenities"})
    public static class Full {
        private int id;
        private String name;
        private String description;
        private String brand;
        private Address address;       // блок {}
        private Contacts contacts;     // блок {}
        private ArrivalTime arrivalTime; // блок {}
        private List<String> amenities;  // массив []

        public Full() {}

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getBrand() { return brand; }
        public void setBrand(String brand) { this.brand = brand; }
        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }
        public Contacts getContacts() { return contacts; }
        public void setContacts(Contacts contacts) { this.contacts = contacts; }
        public ArrivalTime getArrivalTime() { return arrivalTime; }
        public void setArrivalTime(ArrivalTime arrivalTime) { this.arrivalTime = arrivalTime; }
        public List<String> getAmenities() { return amenities; }
        public void setAmenities(List<String> amenities) { this.amenities = amenities; }

        // ---- Вложенные блоки ----

        @JsonPropertyOrder({"houseNumber", "street", "city", "country", "postCode"})
        public static class Address {
            private int houseNumber;
            private String street;
            private String city;
            private String country;
            private String postCode;

            public Address() {}

            public Address(int houseNumber, String street, String city, String country, String postCode) {
                this.houseNumber = houseNumber;
                this.street = street;
                this.city = city;
                this.country = country;
                this.postCode = postCode;
            }

            public int getHouseNumber() { return houseNumber; }
            public void setHouseNumber(int houseNumber) { this.houseNumber = houseNumber; }
            public String getStreet() { return street; }
            public void setStreet(String street) { this.street = street; }
            public String getCity() { return city; }
            public void setCity(String city) { this.city = city; }
            public String getCountry() { return country; }
            public void setCountry(String country) { this.country = country; }
            public String getPostCode() { return postCode; }
            public void setPostCode(String postCode) { this.postCode = postCode; }
        }

        public static class Contacts {
            private String phone;
            private String email;

            public Contacts() {}

            @JsonPropertyOrder({"phone", "email"})
            public Contacts(String phone, String email) {
                this.phone = phone;
                this.email = email;
            }

            public String getPhone() { return phone; }
            public void setPhone(String phone) { this.phone = phone; }
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
        }

        @JsonPropertyOrder({"checkIn", "checkOut"})
        public static class ArrivalTime {
            private String checkIn;
            private String checkOut;

            public ArrivalTime() {}

            public ArrivalTime(String checkIn, String checkOut) {
                this.checkIn = checkIn;
                this.checkOut = checkOut;
            }

            public String getCheckIn() { return checkIn; }
            public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
            public String getCheckOut() { return checkOut; }
            public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
        }
    }
}