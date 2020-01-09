package com.ticketsCode.ticket.Views;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView frame = new LoginView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private JPanel contentPane;
    private JLabel lblUser,lblPassword;
    public JTextField tfUser;
    public JPasswordField tfPassword;
    public JButton btnLogin;

    public LoginView(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(500,250,400,250);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblUser = new JLabel("User : ");
        lblUser.setBounds(90,62,100,25);
        contentPane.add(lblUser);

        lblPassword = new JLabel("Password : ");
        lblPassword.setBounds(61,100,100,25);
        contentPane.add(lblPassword);

        tfUser = new JTextField();
        tfUser.setBounds(140,62,150,30);
        contentPane.add(tfUser);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(140,100,150,30);
        contentPane.add(tfPassword);

        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(150,150,100,30);
        contentPane.add(btnLogin);
        setVisible(true);

    }

}
