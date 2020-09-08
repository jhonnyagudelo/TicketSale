package com.ticketsCode.ticket.Models.Vo;

import java.sql.Time;
import java.util.Date;

public class QrVO {
    private Date buy;
    private Time hour;
    private Integer company;
    private String license;
    private String name;
    private Integer conduce;
    private Integer ticket_id;


    public QrVO( Integer company, String destination, String license, Date buy, Time hour, Integer conduce, Integer ticket_id, String name){
        super();
        this.conduce = conduce;
        this.company = company;
        this.name = name;
        this.license = license;
        this.buy = buy;
        this.hour = hour;
        this.ticket_id = ticket_id ;
    }

    public QrVO() {

    }

    public Integer getConduce(int conduce) {
        return this.conduce;
    }

    public void setConduce(Integer conduce) {
        this.conduce = conduce;
    }

    public Integer getCompany(int company) {
        return this.company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public String getDestination(String destination) {
        return this.name;
    }

    public void setDestination(String destination) {
        this.name = name;
    }

    public String getLicense(String license) {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Date getBuy(java.sql.Date buy) {
        return this.buy;
    }

    public void setBuy(Date buy) {
        this.buy = buy;
    }

    public Time getHour(Time time) {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    @Override
    public String toString() {
        return "QrVO{" +
                "buy=" + buy +
                ", hour=" + hour +
                ", company=" + company +
                ", license='" + license +
                ", destination=" + name +
                ", Identification=" + ticket_id +
                ", conduce=" + conduce +
                '}';
    }
}
