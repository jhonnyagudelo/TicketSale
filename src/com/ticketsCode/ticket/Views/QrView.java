package com.ticketsCode.ticket.Views;

import com.google.zxing.WriterException;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Util.QrGenerate;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class QrView extends JFrame {
    private VehicleVO autoBusVO;


    public QrView() {

    }

    public boolean QrView(VehicleVO autoBusVO) throws WriterException {
        this.autoBusVO = autoBusVO;
        try {
            QrGenerate qrGenerate = new QrGenerate();
            BufferedImage image = qrGenerate.createQR(autoBusVO + "", 300, 300);
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



//    public void QrView(String vehicle, String license, String capacity, String company ) throws WriterException {
//        QrGenerate qrGenerate = new QrGenerate();
//        BufferedImage image = qrGenerate.createQR( vehicle + " "+ license + " " + capacity + " " + company + " " ,300, 300);
//        ImageIcon icon = new ImageIcon(image);
//        JLabel label  = new JLabel("");
//
//        label.setIcon(icon);
//        this.setIconImage(image);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setTitle("QR");
//        this.getContentPane().add(label);
//        this.pack();
//    }

}
