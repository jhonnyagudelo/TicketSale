package com.ticketsCode.ticket.Models.Vo;

import sun.awt.ConstrainableGraphics;

public class DestinationsVO {
    private Integer destination_id;
    private String name;
    private Integer destination_code;
    private String active;

    public DestinationsVO(Integer destination_id, String name, Integer destination_code, String active){
        this.destination_id = destination_id;
        this.name = name;
        this.destination_code = destination_code;
        this.active = active;
    }

    public DestinationsVO(Integer destination_id, String name) {
        this.destination_id = destination_id;
        this.name = name;
    }

    public Integer getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(Integer destination_id) {
        this.destination_id = destination_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGetDestination_code() {
        return destination_code;
    }

    public void setGetDestination_code(Integer getDestination_code) {
        this.destination_code = destination_code;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return  destination_id + " - " + name ;
    }
}
