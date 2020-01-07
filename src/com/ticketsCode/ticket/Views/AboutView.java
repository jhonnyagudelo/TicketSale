package com.ticketsCode.ticket.Views;

import javax.swing.*;
import java.awt.*;

public class AboutView extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AboutView frame = new AboutView();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private JPanel contentPane;



}
