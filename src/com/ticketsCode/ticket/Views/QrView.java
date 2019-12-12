package com.ticketsCode.ticket.Views;

import com.google.zxing.WriterException;
import com.ticketsCode.ticket.Util.QrGenerate;

import javax.swing.*;
import java.awt.image.BufferedImage;


public class QrView extends JFrame {
    private JLabel QrLabel;
    public QrView() {

    }

    public void Qr(String qrDAO) {
        try {
            boolean finished = false;
            QrGenerate qrGenerate = new QrGenerate();
            BufferedImage image = qrGenerate.createQR(qrDAO + "", 200, 200);
            System.out.println("primer: " + qrDAO);

            ImageIcon icon = new ImageIcon(image);

            QrLabel = new JLabel("");


            QrLabel.setIcon(icon);
            this.setIconImage(image);
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.setTitle("QR");
            this.getContentPane().add(QrLabel);
            this.pack();
            System.out.println("QR2: " + QrLabel);
        }catch (WriterException e){
            System.out.print("error QR: " + e.getMessage());
            e.printStackTrace(System.err);
        } catch (Exception e){
            System.out.print("error QR: " + e.getMessage());
            e.printStackTrace(System.err);
        }

    }

}
