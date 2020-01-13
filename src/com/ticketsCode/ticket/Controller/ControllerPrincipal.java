package com.ticketsCode.ticket.Controller;

import com.google.zxing.WriterException;
import com.ticketsCode.ticket.Models.Dao.*;
import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.*;
import com.ticketsCode.ticket.Util.*;
//import com.ticketsCode.ticket.Util.PrintEpson;
import com.ticketsCode.ticket.Views.*;

public class ControllerPrincipal {
    public static void main(String[] args) throws WriterException {
        new DataBaseConnection("tiquetes");

        //Login
        Login login = new Login();
        UsersVO usersVO = new UsersVO();
        LoginView loginView = new LoginView();
        AuthorizationDAO authorizationDAO = new AuthorizationDAO();


        AddUsers add = new AddUsers();
        UsersTotal usersTotal = new UsersTotal();
        UserDAO userDAO = new UserDAO(add, usersTotal);

        // Vehiculos
        ListVehicle autoBus = new ListVehicle();
        VehicleDAO autoBusDAO = new VehicleDAO(autoBus);
        VehicleVO autoBusVO = new VehicleVO();

        //QR
        QrDAO qrDAO = new QrDAO();

        //venta
        TicketSales ticketSales = new TicketSales();
        TicketDAO ticketDAO = new TicketDAO(ticketSales);
        TicketVO ticketVO = new TicketVO();


        PdfPrint pdfPrint = new PdfPrint(qrDAO,login);
        TravelHistory travelHistory = new TravelHistory();
        DataExport dataExport = new DataExport();


        ExportExcel exportExcel = new ExportExcel();
        TotalTravel totalTravel = new TotalTravel();
        RoutePercentage route = new RoutePercentage();;


//
        //Busqueda vehicular
        SearchVehicle search = new SearchVehicle();
        SearchDAO searchDAO = new SearchDAO(search, travelHistory,totalTravel);

        QrImg qrImg = new QrImg();

        ExcelPercentage percentage = new ExcelPercentage();
        ExcelTotal excelTotal = new ExcelTotal();
        ExcelUtil excelUtil = new ExcelUtil(searchDAO,dataExport);

        ControllerTicket ct = new ControllerTicket(ticketSales,ticketDAO,ticketVO, qrDAO,pdfPrint , qrImg);
        ControllerMenu cm = new ControllerMenu(autoBus,search,ticketSales,travelHistory,exportExcel,add,usersVO,usersTotal,login);
        ControllerSearch cs = new ControllerSearch(searchDAO,search,autoBusVO, dataExport, travelHistory, excelUtil, exportExcel,totalTravel, excelTotal, percentage,route);
        ControllerVehicle cv = new ControllerVehicle(autoBus,autoBusDAO,autoBusVO,search,searchDAO);
        ControllerAuth csr = new ControllerAuth(authorizationDAO,loginView,login,ticketSales);
        ControllerUsers cus = new ControllerUsers(add,userDAO,usersVO,usersTotal);


    }

}