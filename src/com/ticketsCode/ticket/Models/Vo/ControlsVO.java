package com.ticketsCode.ticket.Models.Vo;

public class ControlsVO {
    private Integer control_id;
    private String code;
    private String name;

    public ControlsVO(Integer control_id, String code, String name){
        super();
        this.control_id = control_id;
        this.code = code;
        this.name = name;
    }

    public ControlsVO(String code, String name) {
        this.code = code;
        this.name = name;
    }



    public Integer getControl_id() {
        return control_id;
    }

    public void setControl_id(Integer control_id) {
        this.control_id = control_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
