package com.ticketsCode.ticket.Views;

import com.ticketsCode.ticket.Models.Vo.CompanyVO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TotalTravel extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TotalTravel frame = new TotalTravel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private JPanel contentPane;
    private JDesktopPane registrationPane;
    private JLabel  lblDateEnd, lblDateStart;
    public JButton btnExport;
    public JComboBox selectCompany;
    public DefaultComboBoxModel dcbm;


    //Menu
    private JMenuBar menuBar;
    public JDateChooser calendarStar, calendarEnd;
    public JMenu ulFile, ulTickets, ulVehicles, ulHelp;
    public JMenuItem liClose, liList, liRegistration, liSale, liAbout, liExport, liDelete;

    public TotalTravel() {
        setTitle("historial de total");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(480, 200, 480, 300);

        contentPane = new JPanel();
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
        ulHelp = new JMenu("Ayuda");
        menuBar.add(ulHelp);

        //item Menu
        //item Menu Archivo
        liClose = new JMenuItem("Cerrar");
        ulFile.add(liClose);
        //item menu venta
        liSale = new JMenuItem("Venta tiquetes");
        ulTickets.add(liSale);
        liList = new JMenuItem("Consulta");
        ulTickets.add(liList);
        //item menu vehiculos
        liRegistration = new JMenuItem("Registro vehicular");
        ulVehicles.add(liRegistration);

        liExport = new JMenuItem("Historial vehicular");
        ulTickets.add(liExport);
        //acerca de
        liAbout = new JMenuItem("Acerca de.");
        ulHelp.add(liAbout);

        registrationPane = new JDesktopPane();
        registrationPane.setBounds(20, 28, 420, 180);
        registrationPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        registrationPane.setBorder(BorderFactory.createTitledBorder("Datos a exportar"));
        registrationPane.setBackground(SystemColor.control);
        contentPane.add(registrationPane);


        selectCompany = new JComboBox();
        selectCompany.setBounds(150, 35, 148, 25);
        dcbm = new DefaultComboBoxModel();
        registrationPane.add(selectCompany);
        selectCompany.addItem("Elige una empresa");


        lblDateStart = new JLabel("Fecha inicial:");
        lblDateStart.setBounds(10, 90, 80, 25);
        registrationPane.add(lblDateStart);

        lblDateEnd = new JLabel("Fecha final:");
        lblDateEnd.setBounds(220, 90, 80, 25);
        registrationPane.add(lblDateEnd);

        calendarStar = new JDateChooser();
        calendarStar.setBounds(90, 90, 118, 23);
        registrationPane.add(calendarStar);

        calendarEnd = new JDateChooser();
        calendarEnd.setBounds(290, 90, 118, 23);
        registrationPane.add(calendarEnd);

        btnExport = new JButton("Exportar");
        btnExport.setBounds(160, 140, 120, 25);
        registrationPane.add(btnExport);

    }


}








