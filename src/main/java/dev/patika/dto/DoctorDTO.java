package dev.patika.dto;

import java.util.Set;

public class DoctorDTO {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private Set<Long> availableDates;

    // Getters and Setters

    public Set<Long> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(Set<Long> availableDates) {
        this.availableDates = availableDates;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
