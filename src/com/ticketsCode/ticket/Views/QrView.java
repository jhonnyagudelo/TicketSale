package com.ticketsCode.ticket.Views;

import com.google.zxing.WriterException;
import com.ticketsCode.ticket.Controller.ControllerPrincipal;
import com.ticketsCode.ticket.Models.Dao.QrDAO;
import com.ticketsCode.ticket.Models.Vo.QrVO;
import com.ticketsCode.ticket.Models.Vo.TicketVO;
import com.ticketsCode.ticket.Util.QrGenerate;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class QrView extends JFrame {
    private TicketVO ticketVO;
    private QrDAO qrDAO;

    public QrView() {

    }

    public boolean QrView(QrDAO qrDAO) throws WriterException {
        this.qrDAO = qrDAO;
        try {
            QrGenerate qrGenerate = new QrGenerate();
            BufferedImage image = qrGenerate.createQR(qrDAO + "", 300, 300);
            System.out.println(qrDAO);
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel("");

            label.setIcon(icon);
            this.setIconImage(image);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setTitle("QR");
            this.getContentPane().add(label);
            this.pack();
            return true;
        }catch (Exception e){
            System.out.print("error QR" + e.getMessage());
        }
        return false;
    }

}
