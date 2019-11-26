package com.ticketsCode.ticket.Controller;

import com.google.zxing.WriterException;
import com.ticketsCode.ticket.Models.Dao.VehicleDAO;
import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Util.QrGenerate;
import com.ticketsCode.ticket.Views.ListVehicle;
import com.ticketsCode.ticket.Views.QrView;

public class ControllerPrincipal {
    public static void main(String[] args) throws WriterException {
        new DataBaseConnection("tiquetes_trans");

        ListVehicle autoBus = new ListVehicle();
        VehicleDAO autoBusDAO = new VehicleDAO(autoBus);
        VehicleVO autoBusVO = new VehicleVO();
        QrView qrView = new QrView();

        qrView.setVisible(true);

//
        ControllerVehicle cv = new ControllerVehicle(autoBus,autoBusDAO,autoBusVO);
        autoBus.setVisible(true);

    }
    private void iniciar(){

    }
}
