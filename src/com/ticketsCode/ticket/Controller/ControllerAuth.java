package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.AuthorizationDAO;
import com.ticketsCode.ticket.Models.Vo.Login;
import com.ticketsCode.ticket.Util.Hash;
import com.ticketsCode.ticket.Views.LoginView;
import com.ticketsCode.ticket.Views.TicketSales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ControllerAuth implements ActionListener {
     AuthorizationDAO authorizationDAO;
     Login login;
     LoginView loginView;
     TicketSales ticketSales;

    java.util.Date date = new Date();
    DateFormat timeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ControllerAuth(AuthorizationDAO authorizationDAO, LoginView loginView, Login login, TicketSales ticketSales){
        this.loginView = loginView;
        this.authorizationDAO = authorizationDAO;
        this.login = login;
        this.ticketSales = ticketSales;

        this.loginView.btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == loginView.btnLogin){
                if(loginView.tfUser.getText().isEmpty() || loginView.tfPassword.getPassword().toString().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Por favor ingresar datos","Informacion",JOptionPane.ERROR_MESSAGE);
                }else{
                   String pass =new String(loginView.tfPassword.getPassword());
                    String newPass = Hash.sha1(pass);
                    login.setUsername(loginView.tfUser.getText());
                    login.setPassword(newPass);
                    login.setlast_session(timeDate.format(date));
                    if(authorizationDAO.authUser(login)) {
                        ticketSales.setVisible(true);
                        loginView.setVisible(false);
                        JOptionPane.showMessageDialog(null, login.getNames() + " ha Iniciado sesión como: " + login.getTypeCategoty(),"Informacion",JOptionPane.INFORMATION_MESSAGE);
                        LOGGER.log(Level.INFO, "Inicio de sesion");
                    } else {
                        JOptionPane.showMessageDialog(null,  "Usuario o contraseña incorrectos","Informacion",JOptionPane.ERROR_MESSAGE);
                        LOGGER.log(Level.WARNING, "Usuario o Contraseña incorrecta");
                    }
                }
            }

        }catch (Exception ex){
            System.out.println("botonUSer: " + ex.getMessage());
        }

    }
}
