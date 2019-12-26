package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.UserDAO;
import com.ticketsCode.ticket.Models.Vo.LoginVO;
import com.ticketsCode.ticket.Views.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerUser implements ActionListener {
     LoginVO loginVO;
     UserDAO userDAO;
     Login login;

    public ControllerUser(UserDAO userDAO, LoginVO loginVO, Login login){
        this.login = login;
        this.userDAO = userDAO;
        this.loginVO = loginVO;


        this.login.btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == login.btnLogin){
                userDAO.authUser();
            }
        }catch (Exception ex){
            System.out.println("botonUSer: " + ex.getMessage());
        }

    }
}
