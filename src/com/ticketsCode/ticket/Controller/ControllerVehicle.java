package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.VehicleDAO;
import com.ticketsCode.ticket.Models.Vo.CompanyVO;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.ListVehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerVehicle implements ActionListener {
    ListVehicle autoBus;
    VehicleVO autoBusVO;
    VehicleDAO autoBusDAO;




    public ControllerVehicle(ListVehicle autoBus, VehicleDAO autoBusDAO, VehicleVO autoBusVO){
        this.autoBus = autoBus;
        this.autoBusVO = autoBusVO;
        this.autoBusDAO = autoBusDAO;
        this.autoBus.btnUpdate.addActionListener(this);
        this.autoBus.btnSave.addActionListener(this);
        this.autoBus.btnDelete.addActionListener(this);
        this.autoBus.btnClear.addActionListener(this);
        this.autoBus.selectCompany.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    try{
        if(e.getSource() == autoBus.btnSave){
            autoBusVO.setInternal_number(Integer.parseInt(autoBus.tfInternalNumber.getText()));
            autoBusVO.setLicense(autoBus.tfLicense.getText());
            autoBusVO.setCapacity(Integer.parseInt(autoBus.tfCapacity.getText()));
            autoBusVO.setCompany(autoBus.selectCompany.getSelectedIndex());
            if(autoBusDAO.vehiculeRecorder(autoBusVO)){
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                tfClear();
                autoBusDAO._loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Registro No Guardado");
                tfClear();
            }
        }
        }catch (Exception e1){
                System.out.println("Error Save" + e1.getMessage());
    }

    }

    private void tfClear(){
        this.autoBus.tfInternalNumber.setText("");
        this.autoBus.tfLicense.setText("");
        this.autoBus.tfCapacity.setText("");
    }
}
