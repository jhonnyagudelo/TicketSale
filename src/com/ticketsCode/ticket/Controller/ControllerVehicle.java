package com.ticketsCode.ticket.Controller;

import com.ticketsCode.ticket.Models.Dao.VehicleDAO;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.ListVehicle;

public class ControllerVehicle {
    public ListVehicle getAutoBus() {
        return autoBus;
    }

    public void setAutoBus(ListVehicle autoBus) {
        this.autoBus = autoBus;
    }

    ListVehicle autoBus;


}
