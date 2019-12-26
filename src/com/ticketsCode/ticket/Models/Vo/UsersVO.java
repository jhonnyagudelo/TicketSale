package com.ticketsCode.ticket.Models.Vo;

import sun.security.util.Password;

import java.sql.Timestamp;

public class UsersVO {
    private Integer user_id;
    private String names;
    private String last_name;
    private String password;
    private Integer company;
    private boolean status;
    private String username;

    public  UsersVO(){

    }

    public UsersVO(Integer user_id, String names, String last_name, String password, Integer company, boolean status, String username){
        super();
        this.user_id = user_id;
        this.names = names;
        this.last_name = last_name;
        this.password = password;
        this.company = company;
        this.status = status;
        this.username = username;
    }

    public UsersVO(String username, String password, boolean status) {
        super();
        this.username = username;
        this.password = password;
        this. status = status;

    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
