package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.QrDAO;
import com.ticketsCode.ticket.Models.Dao.TicketDAO;
import com.ticketsCode.ticket.Models.Vo.DataQr;
import com.ticketsCode.ticket.Models.Vo.TicketVO;
import com.ticketsCode.ticket.Util.PdfPrint;
import com.ticketsCode.ticket.Util.QrImg;
//import com.ticketsCode.ticket.Util.PrintEpson;
import com.ticketsCode.ticket.Views.TicketSales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerTicket implements ActionListener {

    TicketVO ticketVO;
    TicketDAO ticketDAO;
    TicketSales ticketSales;
    QrDAO qrDAO;
//    PrintEpson printEpson;
    QrImg qrImg;
    PdfPrint pdfPrint;





    public ControllerTicket(TicketSales ticketSales, TicketDAO ticketDAO, TicketVO ticketVO, QrDAO qrDAO, PdfPrint pdfPrint, QrImg qrImg) {
        this.ticketSales = ticketSales;
        this.ticketDAO = ticketDAO;
        this.ticketVO = ticketVO;
        this.pdfPrint = pdfPrint;
        this.qrDAO = qrDAO;
        this.qrImg = qrImg;

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
            ticketVO.setQuantity(Integer.parseInt(ticketSales.tfQuantity.getText()));
            if (ticketDAO.save(ticketVO)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                tfClear();
                ticketDAO._loadTableSale();
                try {
                    qrImg.qrImagen(qrDAO.QR());
                    qrDAO.dateTickect();
                    if (ticketVO.getQuantity() > 0) {
                        for (int i = 0; i < ticketVO.getQuantity(); i++) {
                            pdfPrint.printTicket();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            } else {
                JOptionPane.showMessageDialog(null, "Registro No Guardado");
                tfClear();
            }

        }



    /**
     * Metodo de limpiar los TexField
     * */
    private void tfClear(){
        this.ticketSales.tfPassenger.setText("");
        this.ticketSales.tfQuantity.setText("");
        this.ticketSales.tfVehicle.setText("");
        this.ticketSales.selectDestination.setSelectedItem("");
    }
}
