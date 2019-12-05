package com.ticketsCode.ticket.Controller;

import com.google.zxing.WriterException;
import com.ticketsCode.ticket.Models.Dao.QrDAO;
import com.ticketsCode.ticket.Models.Dao.SearchDAO;
import com.ticketsCode.ticket.Models.Dao.TicketDAO;
import com.ticketsCode.ticket.Models.Dao.VehicleDAO;
import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Util.QrGenerate;
import com.ticketsCode.ticket.Views.ListVehicle;
import com.ticketsCode.ticket.Views.QrView;
import com.ticketsCode.ticket.Views.SearchVehicle;
import com.ticketsCode.ticket.Views.TicketSales;

public class ControllerPrincipal {
    public static void main(String[] args) throws WriterException {
        new DataBaseConnection("tiquetes_trans");

        // Vehiculos
        ListVehicle autoBus = new ListVehicle();
        VehicleDAO autoBusDAO = new VehicleDAO(autoBus);
        VehicleVO autoBusVO = new VehicleVO();
        QrView qrView = new QrView();

        //QR
//        QrView qrView = new QrView();
//        QrDAO qrDAO = new QrDAO();
//        qrView.setVisible(true);

        //venta
        TicketSales sale = new TicketSales();
        TicketDAO saleDAO = new TicketDAO(sale);
        sale.setVisible(true);

        //Busqueda vehicular
        SearchVehicle search = new SearchVehicle();
        SearchDAO searchDAO = new SearchDAO(search);
//        search.setVisible(true);

        ControllerMenu cm = new ControllerMenu(autoBus,search,sale);
        ControllerSearch cs = new ControllerSearch(searchDAO,search,autoBusVO,qrView );
        ControllerVehicle cv = new ControllerVehicle(autoBus,autoBusDAO,autoBusVO,search,searchDAO);
//        autoBus.setVisible(true);

    }
    private void iniciar(){

    }
}
