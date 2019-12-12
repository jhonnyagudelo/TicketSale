package com.ticketsCode.ticket.Controller;

import com.google.zxing.WriterException;
import com.ticketsCode.ticket.Models.Dao.QrDAO;
import com.ticketsCode.ticket.Models.Dao.SearchDAO;
import com.ticketsCode.ticket.Models.Dao.TicketDAO;
import com.ticketsCode.ticket.Models.Dao.VehicleDAO;
import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.QrVO;
import com.ticketsCode.ticket.Models.Vo.TicketVO;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Util.PrintEpson;
import com.ticketsCode.ticket.Views.ListVehicle;
import com.ticketsCode.ticket.Views.QrView;
import com.ticketsCode.ticket.Views.SearchVehicle;
import com.ticketsCode.ticket.Views.TicketSales;

public class ControllerPrincipal {
    public static void main(String[] args) throws WriterException {
        new DataBaseConnection("tiquetes");

        // Vehiculos
        ListVehicle autoBus = new ListVehicle();
        VehicleDAO autoBusDAO = new VehicleDAO(autoBus);
        VehicleVO autoBusVO = new VehicleVO();

        //QR
        QrDAO qrDAO = new QrDAO();
        QrView qrView = new QrView();
        QrVO qrVO = new QrVO();
//        qrView.setVisible(true);

        //venta
        TicketSales ticketSales = new TicketSales();
        TicketDAO ticketDAO = new TicketDAO(ticketSales);
        TicketVO ticketVO = new TicketVO();
        ticketSales.setVisible(true);

        //Busqueda vehicular
        SearchVehicle search = new SearchVehicle();
        SearchDAO searchDAO = new SearchDAO(search);

        PrintEpson printEpson = new PrintEpson();


        ControllerTicket ct = new ControllerTicket(ticketSales,ticketDAO,ticketVO,qrView,qrVO,qrDAO, printEpson);
        ControllerMenu cm = new ControllerMenu(autoBus,search,ticketSales);
        ControllerSearch cs = new ControllerSearch(searchDAO,search,autoBusVO );
        ControllerVehicle cv = new ControllerVehicle(autoBus,autoBusDAO,autoBusVO,search,searchDAO);


    }



    private void iniciar(){


    }
}
