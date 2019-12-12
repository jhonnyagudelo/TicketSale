package com.ticketsCode.ticket.Models.Vo;

import java.sql.Time;
import java.util.Date;

public class QrVO {
    private Integer passenger;
    private Integer company;
    private Integer destination_code;
    private String license;
    private Date buy;
    private Time hour;

    public QrVO( Integer passenger, Integer company, Integer destination_code, String license, Date buy, Time hour){
        super();
        this.passenger = passenger;
        this.company = company;
        this.destination_code = destination_code;
        this.license = license;
        this.buy = buy;
        this.hour = hour;
    }

    public QrVO() {

    }

    public Integer getPassenger(int passenger) {
        return this.passenger;
    }

    public void setPassenger(Integer passenger) {
        this.passenger = passenger;
    }

    public Integer getCompany(int company) {
        return this.company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getDestination_code(int destination_code) {
        return this.destination_code;
    }

    public void setDestination_code(Integer destination_code) {
        this.destination_code = destination_code;
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

    @Override
    public String toString() {
        return "QrVO{" +
                "passenger=" + passenger +
                ", company=" + company +
                ", destination_code=" + destination_code +
                ", license='" + license + '\'' +
                ", buy=" + buy +
                ", hour=" + hour +
                '}';
    }
}
