package com.ticketsCode.ticket.Models.Vo;

public class CompanyVO {
    private Integer company_id;
    private String name;
    private Integer nit;


    public CompanyVO(Integer company_id, String name, Integer nit){
        super();
        this.company_id = company_id;
        this.name = name;
        this.nit = nit;
    }

    public CompanyVO() {

    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    @Override
    public String toString() {
        return "CompanyVO{" +
                "company_id=" + company_id +
                ", name='" + name + '\'' +
                '}';
    }
}
