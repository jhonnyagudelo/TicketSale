package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.QrDAO;
import com.ticketsCode.ticket.Models.Dao.SearchDAO;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.QrView;
import com.ticketsCode.ticket.Views.SearchVehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerSearch implements  ActionListener {
    SearchVehicle search;
    SearchDAO searchDAO;
    VehicleVO autoBusVO;


    public ControllerSearch(SearchDAO searchDAO, SearchVehicle search, VehicleVO autoBusVO){
        this.search = search;
        this.searchDAO = searchDAO;
        this.autoBusVO = autoBusVO;
        this.search.btnSearch.addActionListener( this);
    }







    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search.btnSearch){
            autoBusVO.setInternal_number(Integer.parseInt(search.tfVehicle.getText()));
            try {
                if (searchDAO.search(autoBusVO)) {
                    JOptionPane.showMessageDialog(null, "busqueda exitosa");
            }else {
                    JOptionPane.showMessageDialog(null,"Error" );
                }
            }catch (Exception ex) {
                System.out.printf("error delete" + ex.getMessage());
            }
        }

    }
}
