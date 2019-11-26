package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.VehicleDAO;
import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.ListVehicle;

public class ControllerPrincipal {
    public static void main(String[] args) {
        new DataBaseConnection("tiquetes_trans");

        ListVehicle autoBus = new ListVehicle();
        VehicleDAO autoBusDAO = new VehicleDAO(autoBus);
        VehicleVO autoBusVO = new VehicleVO();

        ControllerVehicle cv = new ControllerVehicle(autoBus,autoBusDAO,autoBusVO);
        autoBus.setVisible(true);

    }
    private void iniciar(){

    }
}
