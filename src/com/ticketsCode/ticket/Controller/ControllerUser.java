package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.UserDAO;
import com.ticketsCode.ticket.Models.Vo.UsersVO;
import com.ticketsCode.ticket.Views.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerUser implements ActionListener {
     UsersVO usersVO;
     UserDAO userDAO;
     Login login;

    public ControllerUser(UserDAO userDAO, UsersVO usersVO, Login login){
        this.login = login;
        this.userDAO = userDAO;
        this.usersVO = usersVO;

        this.login.btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == login.btnLogin){

            }
        }catch (Exception ex){

        }

    }
}
