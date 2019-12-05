package com.ticketsCode.ticket.Views;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchVehicle extends  JFrame{

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SearchVehicle frame = new SearchVehicle();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private JPanel contentPane;
    private JDesktopPane registrationPane;
    private JLabel lblVehiculo;
    public JTextField tfVehicle;
    public JButton btnSearch;
    public DefaultTableModel dtm;
    private JScrollPane scroll;
    public  Object[][] data;
    public String[] headBoard;
    public JTable table;
    //Menu
    private JMenuBar menuBar;
    public JMenu ulFile, ulTickets, ulVehicles, ulHelp;
    public JMenuItem liClose, liList, liRegistration, liSale, liAbout,liList1, liDelete;

    public SearchVehicle(){
        setTitle("Venta de tiquetes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150,100,1100,300);

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

        liDelete = new JMenuItem("Eliminar");
        ulVehicles.add(liDelete);
        ulVehicles.add(liDelete);

        liList1 = new JMenuItem("Consulta");
        ulVehicles.add(liList1);
        //acerca de
        liAbout = new JMenuItem("Acerca de.");
        ulHelp.add(liAbout);

        registrationPane = new JDesktopPane();
        registrationPane.setBounds(20,60, 400, 100);
        registrationPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null ,null));
        registrationPane.setBackground(SystemColor.control);
        contentPane.add(registrationPane);

        lblVehiculo = new JLabel("Vehiculo: ");
        lblVehiculo.setBounds(40,35,120,25);
        registrationPane.add(lblVehiculo);

        tfVehicle = new JTextField();
        tfVehicle.setBounds(100,35,120,25);
        registrationPane.add(tfVehicle);


        btnSearch = new JButton("Search");
        btnSearch.setBounds(240,35,120,25);
        registrationPane.add(btnSearch);

        //Tabla
        scroll = new JScrollPane();
        headBoard = new String[] {"Numero interno", "Placa", "Capacidad", "Compañia", "Activo"};
        dtm = new DefaultTableModel(data,headBoard);
        scroll.setBounds(450, 20, 600, 180);

        getContentPane().add(scroll);
        table = new JTable(dtm);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        scroll.setViewportView(table);
        contentPane.add(scroll);
    }


}
