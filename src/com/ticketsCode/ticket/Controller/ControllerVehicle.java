package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.SearchDAO;
import com.ticketsCode.ticket.Models.Dao.VehicleDAO;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.ListVehicle;
import com.ticketsCode.ticket.Views.SearchVehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerVehicle implements ActionListener {
     ListVehicle autoBus;
     VehicleVO autoBusVO;
     VehicleDAO autoBusDAO;

     SearchVehicle search;
     SearchDAO searchDAO;


    public ControllerVehicle(ListVehicle autoBus, VehicleDAO autoBusDAO, VehicleVO autoBusVO, SearchVehicle search, SearchDAO searchDAO) {
        this.autoBus = autoBus;
        this.autoBusVO = autoBusVO;
        this.autoBusDAO = autoBusDAO;
        this.searchDAO = searchDAO;
        this.search = search;
        this.autoBus.btnUpdate.addActionListener(this);
        this.autoBus.btnSave.addActionListener(this);
        this.autoBus.btnDelete.addActionListener(this);
        this.autoBus.btnClear.addActionListener(this);
        this.autoBus.selectCompany.addActionListener(this);
        this.search.btnSearch.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            /**
             * esta funcion es para guardar lo vehiculos
             * @Param btnSave guarda la informacion
             * */

            if (e.getSource() == autoBus.btnSave) {
                autoBusVO.setInternal_number(Integer.parseInt(autoBus.tfInternalNumber.getText()));
                autoBusVO.setLicense(autoBus.tfLicense.getText());
                autoBusVO.setCapacity(Integer.parseInt(autoBus.tfCapacity.getText()));
                autoBusVO.setCompany(autoBus.selectCompany.getSelectedIndex());
                if (autoBusDAO.vehiculeRecorder(autoBusVO)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    tfClear();
                    autoBusDAO._loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Registro No Guardado");
                    tfClear();
                }
            }
        } catch (Exception e1) {
            System.out.println("Error Save" + e1.getMessage());
        }
        //Bonton de limpiar
        if (e.getSource() == autoBus.btnClear) {
            tfClear();
        }

//        if(e.getSource() == autoBus.btnDelete){
//            int messengerDelete = JOptionPane.showConfirmDialog(null,"Eliminar producto","Si/No",JOptionPane.YES_NO_CANCEL_OPTION);
//                if( autoBus.table.getSelectedRow() != -1){
//                    autoBus.dtm.removeRow(autoBus.table.getSelectedRow());
//                }
//        }

        if (e.getSource() == autoBus.btnDelete) {

            JOptionPane.showConfirmDialog(null, "¿Eliminar producto?", "Si/No", JOptionPane.YES_NO_CANCEL_OPTION);
            int fila = autoBus.table.getSelectedRow();
            int id = Integer.parseInt(autoBus.table.getValueAt(fila, 0).toString());
            autoBusVO.setInternal_number(id);
            try {
                if (autoBusDAO.delete(autoBusVO)) {
                    JOptionPane.showMessageDialog(null, "Eliminado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error");
                }
                autoBusDAO._loadTable();
            } catch (Exception ex) {
                System.out.printf("error delete" + ex.getMessage());
            }
        }
    }


    /**
     * Metodo de limpiar los TexField
     */
    private void tfClear() {
        this.autoBus.tfInternalNumber.setText("");
        this.autoBus.tfLicense.setText("");
        this.autoBus.tfCapacity.setText("");
    }

}
