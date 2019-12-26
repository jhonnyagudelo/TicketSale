package com.ticketsCode.ticket.Models.Vo;


import java.sql.Date;

public class DataExport extends TicketVO {
    private Date dateStart;
    private Date dateEnd;
    private Integer vehicle;

    public  DataExport(){

    }


    public DataExport(Integer vehicle, Date dateStart , Date dateEnd) {
        super(vehicle);
        this.vehicle = vehicle;
        this.dateStart = dateStart;
        this. dateEnd = dateEnd;
    }


    @Override
    public Integer getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
       this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return  "" + dateStart +
                ", " + dateEnd +
                ", "+ vehicle;
    }


}
