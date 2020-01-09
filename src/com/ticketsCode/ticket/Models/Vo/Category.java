package com.ticketsCode.ticket.Models.Vo;

public enum Category {
    ElEGIR("Elige una opción"), ADMINISTRADOR("Administrador"), CONTROL("Control");

    private String Category;

    Category(String Category) {
        this.Category = Category;


    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    @Override
    public String toString() {
        return Category;
    }
}