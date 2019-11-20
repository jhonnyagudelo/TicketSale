package com.ticketsCode.ticket.Models.Vo;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

public class Details_ticketVO {
    private Integer ticket_details_id;
    private Integer ticket;
    private Integer destination;
    private Integer quantity;

    public Details_ticketVO(){

    }
    public Details_ticketVO(Integer ticket_details_id, Integer ticket, Integer destination, Integer quantity){
        this.ticket_details_id = ticket_details_id;
        this.ticket = ticket;
        this.destination = destination;
        this.quantity = quantity;
    }

    public Integer getTicket_details_id() {
        return ticket_details_id;
    }

    public void setTicket_details_id(Integer ticket_details_id) {
        this.ticket_details_id = ticket_details_id;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
