package com.ticketsCode.ticket.Models.Vo;

import com.ticketsCode.ticket.Views.TicketSales;
import sun.security.krb5.internal.Ticket;

import java.util.Date;
import java.util.Timer;

public class TicketVO extends Ticket_detailsVO {
    private Integer ticket_id;
    private Integer company;
    private Integer passenger;
    private Integer origin;
    private Integer destination;
    private Date date;
    private Timer hour;
    private Integer vehicle;


    public TicketVO(Integer passenger,Integer origin, Integer destination, Integer vehicle,Integer quantity){
        super(destination,quantity);
            this.passenger = passenger;
            this.origin = origin;
            this.vehicle = vehicle;
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

    @Override
    public Integer getDestination() {
        return destination;
    }

    @Override
    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "TicketVO{" +
                ", passenger=" + passenger +
                ", company=" + company +
                ", destination=" + destination +
                ", vehicle=" + vehicle +
                ", hour=" + hour +
                '}';
    }
}
