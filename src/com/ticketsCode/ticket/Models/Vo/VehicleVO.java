package com.ticketsCode.ticket.Models.Vo;

public class VehicleVO {
    private Integer vehicle_id;
    private Integer internal_number;
    private String license;
    private Integer capacity;
    private Integer company;
    private Boolean active;

    public VehicleVO() {

    }

    public VehicleVO(Integer vehicle_id, Integer internal_number, String license, Integer capacity, Integer company, Boolean active) {
        this.vehicle_id = vehicle_id;
        this.internal_number = internal_number;
        this.license = license;
        this.capacity = capacity;
        this.company = company;
        this.active = active;
    }


    public VehicleVO(Integer vehicle_id, Integer internal_number) {
        this.vehicle_id = vehicle_id;
        this.internal_number = internal_number;
    }


    public VehicleVO(int internal_number, String license, int capacity, int company, boolean active) {
        this.internal_number = internal_number;
        this.license = license;
        this.capacity = capacity;
        this.company = company;
    }


    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Integer getInternal_number() {
        return internal_number;
    }

    public void setInternal_number(Integer internal_number) {
        this.internal_number = internal_number;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return internal_number + "";
    }

}


