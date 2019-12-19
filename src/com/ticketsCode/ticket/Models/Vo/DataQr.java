package com.ticketsCode.ticket.Models.Vo;

public class DataQr {
    private String company;
    private String destiny;

    public DataQr(){
    }

    public DataQr(String company, String destiny) {
        this.company = company;
        this.destiny = destiny;

    }

    public String getCompany() {
        return company;
    }

    public void setCompany( String nombre) {
         company = nombre;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String nombre) {
        destiny = nombre;
    }

    @Override
    public String toString() {
        return company + ',' + " " + destiny ;
    }

//    public static void main(String[] args) {
//        DataQr dataQr = new DataQr("jhonny");
//        dataQr.setCompany("Coodetrans");
//        dataQr.setDestiny("Palmira");
//        System.out.println("El destino es: " + dataQr.getDestiny());
//
//
//    }


}
