package com.ticketsCode.ticket.Models.Vo;

/**
 * <h1>Tickets</h1>
 * Ticket es una clase padre abstracta
 * <p>
 *Esta clase es la base de la familia tickets.
 * </p>
 *
 * */

public abstract class TicketSales {
    private Integer ticket_id;
    private String company;
    private Integer passenger;
    private Integer origin;

    public TicketSales(){

    }
    public TicketSales(Integer ticket_id, String company, Integer passenger, Integer origin){
        super();
        this.ticket_id = ticket_id;
        this.company = company;
        this.passenger = passenger;
        this.origin = origin;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
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
    /**
     * {@code view()} es un metodo abstracto obligatorio de implementar
     * */
    public abstract void view();
}
