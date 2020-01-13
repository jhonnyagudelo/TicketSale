package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.SearchDAO;
import com.ticketsCode.ticket.Models.Vo.DataExport;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Util.ExcelPercentage;
import com.ticketsCode.ticket.Util.ExcelUtil;
import com.ticketsCode.ticket.Util.ExcelTotal;
import com.ticketsCode.ticket.Views.*;


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
    ExcelUtil excelUtil;
    ExportExcel exportExcel;
    ExcelTotal excelTotal;
    TotalTravel totalTravel;
    ExcelPercentage percentage;
    RoutePercentage route;


    DateFormat timeDate = new SimpleDateFormat("yyyy-MM-dd");

    public ControllerSearch(SearchDAO searchDAO, SearchVehicle search, VehicleVO autoBusVO, DataExport data, TravelHistory travelHistory, ExcelUtil excelUtil, ExportExcel exportExcel, TotalTravel totalTravel, ExcelTotal excelTotal, ExcelPercentage percentage, RoutePercentage route) {
        this.search = search;
        this.searchDAO = searchDAO;
        this.autoBusVO = autoBusVO;
        this.data = data;
        this.travelHistory = travelHistory;
        this.excelUtil = excelUtil;
        this.exportExcel = exportExcel;
        this.totalTravel = totalTravel;
        this.excelTotal = excelTotal;
        this.percentage = percentage;
        this.route = route;

//        this.dataExcel = dataExcel;

        this.route.btnExport.addActionListener(this);
        this.travelHistory.btnSearch.addActionListener(this);
        this.search.btnSearch.addActionListener(this);
        this.exportExcel.btnTotal.addActionListener(this);
        this.exportExcel.btnInternal_number.addActionListener(this);
        this.totalTravel.btnExport.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search.btnSearch) {
            if (search.tfVehicle.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un vehiculo", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                autoBusVO.setInternal_number(Integer.parseInt(search.tfVehicle.getText()));
                if (!searchDAO.search(autoBusVO)) {
                    tfClear();
                    JOptionPane.showMessageDialog(null, "busqueda exitosa");
                } else {
                    JOptionPane.showMessageDialog(null, "vehiculo no existe");
                    tfClear();
                }
            }
        }

        if (e.getSource() == exportExcel.btnInternal_number) {
            JOptionPane.showMessageDialog(null, "Registro por vehiculo");
            travelHistory.setVisible(true);
        }

        if (e.getSource() == exportExcel.btnTotal) {
            JOptionPane.showMessageDialog(null, "Registro por total");
            totalTravel.setVisible(true);
        }

        if (e.getSource() == exportExcel.btnDestiny) {
            JOptionPane.showMessageDialog(null, "Porcentaje por destino");
            route.setVisible(true);
        }


        if (e.getSource() == travelHistory.btnSearch) {
            if (travelHistory.tfVehicle.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Ingresar un dato", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                data.setVehicle(Integer.parseInt(travelHistory.tfVehicle.getText()));
                data.setDateStart(Date.valueOf(timeDate.format(travelHistory.calendarStar.getDate())));
                data.setDateEnd(Date.valueOf(timeDate.format(travelHistory.calendarEnd.getDate())));
                if (!searchDAO.travel_history(data)) {
                    excelUtil.createExcel(data);
                    tfClear();
                    JOptionPane.showMessageDialog(null, "Datos exportados");
                } else {
                    tfClear();
                    JOptionPane.showMessageDialog(null, "No hay datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == totalTravel.btnExport) {
            data.setCompany(totalTravel.selectCompany.getSelectedIndex());
            data.setDateStart(Date.valueOf(timeDate.format(totalTravel.calendarStar.getDate())));
            data.setDateEnd(Date.valueOf(timeDate.format(totalTravel.calendarEnd.getDate())));
            excelTotal.excelVehicle(data);
        }

        if (e.getSource() == route.btnExport) {
            data.setDateStart(Date.valueOf(timeDate.format(route.calendarStar.getDate())));
            data.setDateEnd(Date.valueOf(timeDate.format(route.calendarEnd.getDate())));
            percentage.excelPorcentaje(data);
        }

    }

    @Override
    public void tfClear() {
        this.search.tfVehicle.setText("");
        this.travelHistory.tfVehicle.setText("");
    }

    @Override
    public boolean isStringUpperCase(String str) {
        return false;
    }
}