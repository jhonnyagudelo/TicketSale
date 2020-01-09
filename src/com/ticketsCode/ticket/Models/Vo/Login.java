package com.ticketsCode.ticket.Models.Vo;

public class Login extends UsersVO {
    private String typeCategoty;

    public Login(){ }

    public Login(String name, String username, String password, boolean status, String typeCategoty){
        super(name,username,password,status);
        this.typeCategoty = typeCategoty;
    }

    public String getTypeCategoty() {
        return typeCategoty;
    }

    public void setTypeCategoty(String typeCategoty) {
        this.typeCategoty = typeCategoty;
    }

    @Override
    public String toString() {
        return "typeCategoty='" + typeCategoty ;
    }
}




