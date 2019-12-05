package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.SearchDAO;
import com.ticketsCode.ticket.Views.ListVehicle;
import com.ticketsCode.ticket.Views.SearchVehicle;
import com.ticketsCode.ticket.Views.TicketSales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenu  implements ActionListener {
    ListVehicle autoBus ;
    SearchVehicle search;
    TicketSales sale;


    public ControllerMenu(ListVehicle autoBus, SearchVehicle search, TicketSales sale){
        this.autoBus = autoBus;
        this.search = search;
        this.sale = sale;
        //registro vehiculo
        this.autoBus.liRegistration.addActionListener(this);
        this.sale.liRegistration.addActionListener(this);
        this.search.liRegistration.addActionListener(this);
        //consulta de tiquetes
        this.autoBus.liList1.addActionListener(this);
        this.sale.liList1.addActionListener(this);
        this.search.liList1.addActionListener(this);
        //consulta vehiculos
        this.autoBus.liList.addActionListener(this);
        this.sale.liList.addActionListener(this);
        this.search.liList.addActionListener(this);
        //cerrar
        this.autoBus.liClose.addActionListener(this);
        this.sale.liClose.addActionListener(this);
        this.search.liClose.addActionListener(this);
        //venta
        this.autoBus.liSale.addActionListener(this);
        this.sale.liSale.addActionListener(this);
        this.search.liSale.addActionListener(this);
        //acerca
        this.autoBus.liAbout.addActionListener(this);
        this.sale.liAbout.addActionListener(this);
        this.search.liAbout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sale.liRegistration || e.getSource() == autoBus.liRegistration || e.getSource() == search.liRegistration){
            try {
                    autoBus.setVisible(true);
            }catch (Exception e1){
                System.out.printf("Color: " + e1.getMessage());
            }
        }
        if(e.getSource() == sale.liList1 || e.getSource() == autoBus.liList1 || e.getSource() == search.liList1){
            try {
                search.setVisible(true);
            }catch (Exception e1){
                System.out.printf("Color: " + e1.getMessage());
            }
            if(e.getSource() == sale.liClose || e.getSource() == autoBus.liClose || e.getSource() == search.liClose){
                try {
                    autoBus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    sale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }catch (Exception e1){
                    System.out.printf("Color: " + e1.getMessage());
                }
            }
        }
    }
}
