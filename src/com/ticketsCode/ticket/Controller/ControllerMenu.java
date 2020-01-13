package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Vo.Login;
import com.ticketsCode.ticket.Models.Vo.UsersVO;
import com.ticketsCode.ticket.Views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenu implements ActionListener {
    ListVehicle autoBus;
    SearchVehicle search;
    TicketSales sale;
    TravelHistory travelHistory;
    ExportExcel exportExcel;
    AddUsers add;
    UsersVO user;
    UsersTotal usersTotal;
    Login login;


    public ControllerMenu(ListVehicle autoBus, SearchVehicle search, TicketSales sale, TravelHistory travelHistory, ExportExcel exportExcel, AddUsers add, UsersVO user, UsersTotal usersTotal, Login login) {
        this.autoBus = autoBus;
        this.search = search;
        this.sale = sale;
        this.travelHistory = travelHistory;
        this.exportExcel = exportExcel;
        this.add = add;
        this.user = user;
        this.usersTotal = usersTotal;
        this.login = login;

        //registro vehiculo
        this.autoBus.liRegistration.addActionListener(this);
        this.sale.liRegistration.addActionListener(this);
        this.search.liRegistration.addActionListener(this);
        //consulta de tiquetes
        this.autoBus.liList1.addActionListener(this);
        this.sale.liList1.addActionListener(this);
        this.search.liList1.addActionListener(this);
        //consulta vehiculos

        //Exportar
        this.sale.liExport.addActionListener(this);
        this.search.liExport.addActionListener(this);
        this.autoBus.liExport.addActionListener(this);

        //Usuarios
        this.sale.liUsers.addActionListener(this);

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
        if (e.getSource() == sale.liRegistration || e.getSource() == autoBus.liRegistration || e.getSource() == search.liRegistration) {
            if (login.getTypeCategoty().equals("Administrador")) {
                autoBus.setVisible(true);
            } else {
                autoBus.setVisible(false);
                JOptionPane.showMessageDialog(null, " no puedes hacer cambios");
                System.out.println("No eres admin");
            }
        }
            if (e.getSource() == sale.liList1 || e.getSource() == autoBus.liList1 || e.getSource() == search.liList1) {
                try {
                    search.setVisible(true);
                } catch (Exception e1) {
                    System.out.printf("Color: " + e1.getMessage());
                }
            }
            if (e.getSource() == sale.liClose || e.getSource() == autoBus.liClose || e.getSource() == search.liClose) {
                try {
                    autoBus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    sale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } catch (Exception e1) {
                    System.out.printf("Color: " + e1.getMessage());
                }
            }
            if (e.getSource() == sale.liExport || e.getSource() == autoBus.liExport || e.getSource() == search.liExport) {
                try {
                    exportExcel.setVisible(true);
                } catch (Exception e1) {
                    System.out.println("history panel: " + e1.getMessage());
                }
            }
            if (e.getSource() == sale.liUsers || e.getSource() == autoBus.liUsers || e.getSource() == search.liUsers) {
                if (login.getTypeCategoty().equals("Administrador")) {
                    usersTotal.setVisible(true);
                } else{
                    usersTotal.setVisible(false);
                    JOptionPane.showMessageDialog(null, "No eres administrador, no puedes hacer cambios");
                    System.out.println("No eres admin");
                }
            }
        }
    }
