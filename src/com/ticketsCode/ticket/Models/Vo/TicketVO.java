package com.ticketsCode.ticket.Models.Vo;

import com.ticketsCode.ticket.Views.TicketSales;
import sun.security.krb5.internal.Ticket;

import java.util.Date;
import java.util.Timer;

public class TicketVO {
    private Integer ticket_id;
    private Integer company;
    private Integer passenger;
    private Integer origin;
    private Integer destination;
    private Date date;
    private Timer hour;
    private Integer vehicle;
    private Integer quantiy;


    public TicketVO(){

    }

    public TicketVO(Integer passenger,Integer origin, Integer destination, Integer vehicle,Integer quantity){
            this.passenger = passenger;
            this.origin = origin;
            this.vehicle = vehicle;
            this.quantiy = quantity;
            this.destination = destination;
    }


    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getPassenger() {
        return passenger;
    }

    public void setPassenger(Integer passenger) {
        this.passenger = passenger;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timer getHour() {
        return hour;
    }

    public void setHour(Timer hour) {
        this.hour = hour;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(Integer quantiy) {
        this.quantiy = quantiy;
    }


    @Override
    public String toString() {
        return  "company=" + company +
                ", passenger=" + passenger +
                ", destination=" + destination +
                ", date=" + date +
                ", hour=" + hour +
                ", vehicle=" + vehicle +
                ", origen = " + origin;
    }
}
