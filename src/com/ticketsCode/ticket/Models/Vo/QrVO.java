package com.ticketsCode.ticket.Models.Vo;

import com.ticketsCode.ticket.Models.Dao.QrDAO;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

public class QrVO {
    private Integer passenger;
    private Integer company;
    private Integer destination_code;
    private String license;
    private Date buy;
    private Time hour;

    public QrVO(){

    }

    public QrVO( Integer passenger, Integer company, Integer destination_code, String license, Date buy, Time hour){
        super();
        this.passenger = passenger;
        this.company = company;
        this.destination_code = destination_code;
        this.license = license;
        this.buy = buy;
        this.hour = hour;
    }

    public Integer getPassenger() {
        return passenger;
    }

    public void setPassenger(Integer passenger) {
        this.passenger = passenger;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getDestination_code() {
        return destination_code;
    }

    public void setDestination_code(Integer destination_code) {
        this.destination_code = destination_code;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Date getBuy() {
        return buy;
    }

    public void setBuy(Date buy) {
        this.buy = buy;
    }

    public Time getHour() {
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
