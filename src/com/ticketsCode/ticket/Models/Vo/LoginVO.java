package com.ticketsCode.ticket.Models.Vo;

public class LoginVO extends UsersVO {
    private String username;
    private String password;
    private boolean status;

    public LoginVO(){}

    public LoginVO(String username, String password, boolean status){
        super(username, password, status);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        password = password;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        status = status;
    }
}
