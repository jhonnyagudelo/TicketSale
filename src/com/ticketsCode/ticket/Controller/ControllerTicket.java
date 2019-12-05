package com.ticketsCode.ticket.Controller;

import com.google.zxing.WriterException;
import com.ticketsCode.ticket.Models.Dao.TicketDAO;
import com.ticketsCode.ticket.Models.Vo.TicketVO;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.QrView;
import com.ticketsCode.ticket.Views.TicketSales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerTicket implements ActionListener {

    TicketVO ticketVO;
    TicketDAO ticketDAO;
    TicketSales ticketSales;
    QrView qrDAO;

    public ControllerTicket(TicketSales ticketSales, TicketDAO ticketDAO, TicketVO ticketVO,QrView qrDAO) {
        this.ticketSales = ticketSales;
        this.ticketDAO = ticketDAO;
        this.ticketVO = ticketVO;
        this.qrDAO = qrDAO;

        this.ticketSales.btnSale.addActionListener(this);
        this.ticketSales.btnClear.addActionListener(this);
        this.ticketSales.btnDelete.addActionListener(this);
        this.ticketSales.btnUpdate.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ticketSales.btnSale) {
            ticketVO.setPassenger(Integer.parseInt(ticketSales.tfPassenger.getText()));
            ticketVO.setOrigin(ticketSales.selectOrigin.getSelectedIndex());
            ticketVO.setDestination(ticketSales.selectDestination.getSelectedIndex());
            ticketVO.setVehicle(Integer.parseInt(ticketSales.tfVehicle.getText()));
            ticketVO.setQuantiy(Integer.parseInt(ticketSales.tfQuantity.getText()));
            if (ticketDAO.save(ticketVO)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                tfClear();
                ticketDAO._loadTableSale();
            }
            try {
                if (qrDAO.QrView(ticketVO)){
                        JOptionPane.showMessageDialog(null, "busqueda QR exitosa");
                        qrDAO.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Registro No Guardado");
                    tfClear();
                }
            } catch (WriterException ex) {
                System.out.println("Error QR: " + ex.getMessage());
            }

        }

    }

    /**
     * Metodo de limpiar los TexField
     * */
    private void tfClear(){
        this.ticketSales.tfPassenger.setText("");
        this.ticketSales.tfQuantity.setText("");
    }
}
