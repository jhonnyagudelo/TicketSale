package com.ticketsCode.ticket.Views;

import com.google.zxing.WriterException;
import com.ticketsCode.ticket.Util.QrGenerate;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class QrView extends JFrame {

    public QrView() throws WriterException {
        QrGenerate qrGenerate = new QrGenerate();
        BufferedImage image = qrGenerate.createQR("data perraaaaas",300, 300);
        ImageIcon icon = new ImageIcon(image);
        JLabel label  = new JLabel("");

        label.setIcon(icon);

        this.setIconImage(image);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("QR");
        this.getContentPane().add(label);
        this.pack();
    }
}
