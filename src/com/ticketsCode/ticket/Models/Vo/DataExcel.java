package com.ticketsCode.ticket.Models.Vo;

public class DataExcel {
    private Integer internal_number;
    private String name;
    private Integer total_tickets;
    private String Date;

    public DataExcel(){

    }

    public Integer getInternal_number() {
        return internal_number;
    }

    public void setInternal_number(Integer internal_number) {
        this.internal_number = internal_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal_tickets() {
        return total_tickets;
    }

    public void setTotal_tickets(Integer total_tickets) {
        this.total_tickets = total_tickets;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "DataExcel{" +
                "internal_number=" + internal_number +
                ", name='" + name + '\'' +
                ", total_tickets=" + total_tickets +
                ", Date='" + Date + '\'' +
                '}';
    }
}
