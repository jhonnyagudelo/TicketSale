package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.UserDAO;
import com.ticketsCode.ticket.Models.Vo.Category;
import com.ticketsCode.ticket.Models.Vo.UsersVO;
import com.ticketsCode.ticket.Util.Hash;
import com.ticketsCode.ticket.Views.AddUsers;
import com.ticketsCode.ticket.Views.UsersTotal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerUsers implements ActionListener {
    AddUsers add;
    UsersTotal usersTotal;
    UserDAO userDAO;
    UsersVO usersVO;


    public ControllerUsers(AddUsers add, UserDAO userDAO, UsersVO usersVO, UsersTotal usersTotal) {
        this.add = add;
        this.userDAO = userDAO;
        this.usersVO = usersVO;
        this.usersTotal = usersTotal;


        this.add.btnAdd.addActionListener(this);
        this.add.btnCancel.addActionListener(this);
        this.add.selecCompany.addActionListener(this);
        this.add.selectControl.addActionListener(this);
        this.add.selectType.addActionListener(this);
        this.usersTotal.btnAdd.addActionListener(this);
//        this.usersTotal.btnModifity.addActionListener(this);
        this.usersTotal.btnDelete.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == usersTotal.btnAdd) {
            add.setVisible(true);
        }
        if (e.getSource() == usersTotal.btnDelete) {
            JOptionPane.showMessageDialog(null, "¿Eliminar usuario?", "Si/No", JOptionPane.YES_NO_CANCEL_OPTION);
            int fila = usersTotal.table.getSelectedRow();
            String userName = usersTotal.table.getValueAt(fila, 2).toString();
            System.out.println("dato: " + userName);
            usersVO.setUsername(userName);
            if (userDAO.delete(usersVO)) {
                JOptionPane.showMessageDialog(null, "Eliminado Exitosamente");
                userDAO.listUSer();
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        if (e.getSource() == add.btnAdd) {
            String pass = new String(add.tfPassword.getPassword());
            String passC = new String(add.tfConfiPassword.getPassword());
            if (add.tfName.getText().isEmpty() || pass.isEmpty() || passC.isEmpty() || add.tfName.getText().isEmpty() || add.tfUsername.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios", "Informacion", JOptionPane.ERROR_MESSAGE);
            } else {
                if (pass.equals(passC)) {
                    usersVO.setNames(add.tfName.getText());
                    usersVO.setLast_name(add.tfLastName.getText());
                    String newPass = Hash.sha1(pass);
                    usersVO.setPassword(newPass);
                    usersVO.setCompany(add.selecCompany.getSelectedIndex());
                    usersVO.setUsername(add.tfUsername.getText());
                    usersVO.setType((Category) add.selectType.getSelectedItem());
                    usersVO.setControl_id(add.selectControl.getSelectedIndex());
                    if (userDAO.addUser(usersVO)) {
                        JOptionPane.showMessageDialog(null, "registro guardado");
                        tfClear();
                        selectControl();
                        userDAO.listUSer();
                    } else if (userDAO.addUser2(usersVO)) {
                        JOptionPane.showMessageDialog(null, "registro guardado");
                        tfClear();
                        selectControl();
                        userDAO.listUSer();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar", "Informacion", JOptionPane.WARNING_MESSAGE);
                        tfClear();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La contraseña es incorrecta", "Informacion", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    public void selectControl() {
        if (add.selectType.getSelectedItem() == "Control") {
            add.selectControl.setEnabled(true);
            usersVO.setControl_id(add.selectControl.getSelectedIndex());
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
