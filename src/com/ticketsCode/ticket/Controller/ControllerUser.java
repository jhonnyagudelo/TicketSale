package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.UserDAO;
import com.ticketsCode.ticket.Models.Vo.UsersVO;
import com.ticketsCode.ticket.Util.Hash;
import com.ticketsCode.ticket.Views.Login;
import com.ticketsCode.ticket.Views.TicketSales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ControllerUser implements ActionListener {
     UserDAO userDAO;
     UsersVO userVo;
     Login login;
     TicketSales ticketSales;


    public ControllerUser(UserDAO userDAO, Login login,UsersVO userVo,TicketSales ticketSales){
        this.login = login;
        this.userDAO = userDAO;
        this.userVo = userVo;
        this.ticketSales = ticketSales;

        this.login.btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == login.btnLogin){
                if(login.tfUser.getText().isEmpty() || login.tfPassword.getPassword().toString().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Por favor ingresar datos");
                }else{
                   String pass =new String(login.tfPassword.getPassword());
//                    String newpass = Hash.sha1(pass);
                    userVo.setUsername(login.tfUser.getText());
                    userVo.setPassword(pass);
                    if(userDAO.authUser(userVo)) {
                        ticketSales.setVisible(true);
                        JOptionPane.showMessageDialog(null,"sesion iniciada a: " + userVo.getNames());
                        LOGGER.log(Level.INFO, "Inicio de sesion");
                    }
                }
            }
        }catch (Exception ex){
            System.out.println("botonUSer: " + ex.getMessage());
        }

    }
}
