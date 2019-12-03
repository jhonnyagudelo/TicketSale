package com.ticketsCode.ticket.Models.Vo;

public class OriginsVO {
    private int origin_id;
    private String name;
    private Boolean active;

    public OriginsVO(int origin_id, String name) {
        this.origin_id = origin_id;
        this.name = name;
    }

    public int getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(int origin_id) {
        this.origin_id = origin_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return origin_id  + " - " + name ;
    }
}
