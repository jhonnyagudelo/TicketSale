package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.SearchDAO;
import com.ticketsCode.ticket.Models.Vo.DataExport;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Util.PoiUtils;
import com.ticketsCode.ticket.Views.SearchVehicle;
import com.ticketsCode.ticket.Views.TravelHistory;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ControllerSearch implements ActionListener, Iuseful {
    SearchVehicle search;
    SearchDAO searchDAO;
    VehicleVO autoBusVO;
    DataExport data;
    TravelHistory travelHistory;
    PoiUtils poiUtils;

    DateFormat timeDate = new SimpleDateFormat("yyyy-MM-dd");

    public ControllerSearch(SearchDAO searchDAO, SearchVehicle search, VehicleVO autoBusVO, DataExport data, TravelHistory travelHistory, PoiUtils poiUtils) {
        this.search = search;
        this.searchDAO = searchDAO;
        this.autoBusVO = autoBusVO;
        this.data = data;
        this.travelHistory = travelHistory;
        this.poiUtils = poiUtils;

        this.travelHistory.btnSearch.addActionListener(this);
        this.search.btnSearch.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == search.btnSearch) {
                if (search.tfVehicle.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un vehiculo", "Error", JOptionPane.ERROR_MESSAGE);
                }
                autoBusVO.setInternal_number(Integer.parseInt(search.tfVehicle.getText()));
                if (!searchDAO.search(autoBusVO)) {
                    tfClear();
                    JOptionPane.showMessageDialog(null, "busqueda exitosa");
                } else {
                    JOptionPane.showMessageDialog(null, "vehiculo no existe");
                    tfClear();
                }
            }
        } catch (Exception ex) {
            System.out.println("errorSearch " + ex.fillInStackTrace());
            System.out.println("error search: " + ex.getMessage());
        }

        try {
            if (e.getSource() == travelHistory.btnSearch ){
                if (travelHistory.tfVehicle.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresar un dato", "Error", JOptionPane.ERROR_MESSAGE);
                }
                data.setVehicle(Integer.parseInt(travelHistory.tfVehicle.getText()));
                data.setDateStart(Date.valueOf(timeDate.format(travelHistory.calendarStar.getDate())));
                data.setDateEnd(Date.valueOf(timeDate.format(travelHistory.calendarEnd.getDate())));
                 searchDAO.travel_history(data);
                    poiUtils.createExcel();
                    tfClear();
                    JOptionPane.showMessageDialog(null, "Datos exportados");
//                } else {
//                    tfClear();
//                    JOptionPane.showMessageDialog(null, "No hay datos", "Error", JOptionPane.ERROR_MESSAGE);
//                }
            }

        } catch (Exception e1) {
            System.out.println("travel erro: " + e1.getMessage());
        }
    }


    @Override
    public void tfClear() {
        this.search.tfVehicle.setText("");
        this.travelHistory.tfVehicle.setText("");
    }

    @Override
    public boolean isStringUpperCase() {
        return false;
    }
}