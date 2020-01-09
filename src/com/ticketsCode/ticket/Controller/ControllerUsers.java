package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.UserDAO;
import com.ticketsCode.ticket.Models.Vo.Category;
import com.ticketsCode.ticket.Models.Vo.UsersVO;
import com.ticketsCode.ticket.Util.Hash;
import com.ticketsCode.ticket.Views.AddUsers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerUsers implements ActionListener {
     AddUsers add;
    UserDAO userDAO;
    UsersVO usersVO;

    public ControllerUsers(AddUsers add,UserDAO userDAO, UsersVO usersVO){
        this.add = add;
        this.userDAO = userDAO;
        this.usersVO = usersVO;

        this.add.btnAdd.addActionListener(this);
        this.add.btnCancel.addActionListener(this);
        this.add.selecCompany.addActionListener(this);
        this.add.selectControl.addActionListener(this);
        this.add.selectType.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add.btnAdd) {
            String pass = new String(add.tfPassword.getPassword());
            String passC = new String(add.tfConfiPassword.getPassword());

            if (add.tfName.getText().isEmpty() || pass.isEmpty() || passC.isEmpty() || add.tfName.getText().isEmpty() || add.tfUsername.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios", "Informacion", JOptionPane.ERROR_MESSAGE);
            } else {

                if (pass.equals(passC)) {
                    usersVO.setNames(add.tfName.getText());
                    usersVO.setUsername(add.tfUsername.getText());
                    String newPass = Hash.sha1(pass);
                    usersVO.setPassword(newPass);
                    usersVO.setLast_name(add.tfLastName.getText());
                    usersVO.setCompany(add.selecCompany.getSelectedIndex());
                    usersVO.setType((Category) add.selectType.getSelectedItem());
                    System.out.println("username: "+ add.tfUsername.getText());
                    if(userDAO.addUser(usersVO)){
                        JOptionPane.showMessageDialog(null,"registro guardado");
                        tfClear();
                    }else {
                        JOptionPane.showMessageDialog(null,"Error al guardar","Informacion",JOptionPane.WARNING_MESSAGE);
                        tfClear();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"La contraseña es incorrecta","Informacion",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    public void tfClear() {
        this.add.tfPassword.setText("");
        this.add.tfConfiPassword.setText("");
        this.add.tfUsername.setText("");
        this.add.tfName.setText("");
        this.add.tfLastName.setText("");
    }
}
