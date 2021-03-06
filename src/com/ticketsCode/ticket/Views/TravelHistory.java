package com.ticketsCode.ticket.Views;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TravelHistory extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TravelHistory frame = new TravelHistory();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private JPanel contentPane;
    private JDesktopPane registrationPane;
    private JLabel lblVehiculo, lblDateEnd, lblDateStart;
    public JTextField tfVehicle;
    public JButton btnSearch;
    public  Object[][] data;
    //Menu
    private JMenuBar menuBar;
    public JDateChooser calendarStar, calendarEnd;
    public JMenu ulFile, ulTickets, ulVehicles, ulHelp,ulConfig;
    public JMenuItem liClose, liUsers, liRegistration, liSale, liAbout, liList1, liExport;

    public TravelHistory(){
        setTitle("historial de ticket");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(480,200,480,300);

        contentPane = new JPanel();
        //getContentPane().add(contentPane);
        setContentPane(contentPane);
        contentPane.setLayout(null);


        //Barra Menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        ulFile = new JMenu("Archivo");
        menuBar.add(ulFile);

        ulTickets = new JMenu("Tiquetes");
        menuBar.add(ulTickets);

        ulVehicles = new JMenu("Vehiculos");
        menuBar.add(ulVehicles);

        ulConfig = new JMenu("Configuracion");
        menuBar.add(ulConfig);

        ulHelp = new JMenu("Ayuda");
        menuBar.add(ulHelp);

        //item Menu
        //item Menu Archivo
        liClose = new JMenuItem("Cerrar");
        ulFile.add(liClose);
        //item menu venta

        liSale = new JMenuItem("Venta tiquetes");
        ulTickets.add(liSale);

        liExport = new JMenuItem("Historial vehicular");
        ulTickets.add(liExport);

        //Config
        liUsers = new JMenuItem("Usuarios");
        ulConfig.add(liUsers);

        //item menu vehiculos
        liRegistration = new JMenuItem("Registro vehicular");
        ulVehicles.add(liRegistration);

        liList1 = new JMenuItem("Consulta");
        ulVehicles.add(liList1);
        //acerca de
        liAbout = new JMenuItem("Acerca de.");
        ulHelp.add(liAbout);


        registrationPane = new JDesktopPane();
        registrationPane.setBounds(20,35, 420, 150);
        registrationPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null ,null));
        registrationPane.setBorder(BorderFactory.createTitledBorder("Datos a exportar"));
        registrationPane.setBackground(SystemColor.control);
        contentPane.add(registrationPane);

        lblVehiculo = new JLabel("Vehiculo:");
        lblVehiculo.setBounds(130,20,120,25);
        registrationPane.add(lblVehiculo);

        lblDateStart = new JLabel("Fecha inicial:");
        lblDateStart.setBounds(10,60,80,25);
        registrationPane.add(lblDateStart);

        lblDateEnd = new JLabel("Fecha final:");
        lblDateEnd.setBounds(220,60,80,25);
        registrationPane.add(lblDateEnd);
//
        tfVehicle = new JTextField();
        tfVehicle.setBounds(185,20,120,25);
        registrationPane.add(tfVehicle);

        calendarStar = new JDateChooser();
        calendarStar.setBounds(90,60,120,25);
        registrationPane.add(calendarStar);

        calendarEnd = new JDateChooser();
        calendarEnd.setBounds(290,60,120,25);
        registrationPane.add(calendarEnd);

        btnSearch = new JButton("Exportar");
        btnSearch.setBounds(160,110,120,25);
        registrationPane.add(btnSearch);

    }


}

