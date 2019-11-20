package com.ticketsCode.ticket.Views;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TicketSales extends JFrame {


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TicketSales frame = new TicketSales();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    private JPanel contentPane;
    private JDesktopPane registrationPane;
    private JLabel lblCompany, lblPassenger, lblOrigin, lblDestination, lblQuantity,lblVehicle;
    public JComboBox selectDestination, selectVehicle, selectOrigin;
    public JTextField tfCompany, tfPassenger, tfQuantity;
    public JButton btnSale, btnUpdate, btnDelete, btnClear;
    private DefaultComboBoxModel dcbm;
    private DefaultTableModel dtm;
    private JScrollPane scroll;
    public  Object[][] data;
    public String[] headBoard;
    public JTable table;
    //Menu
    private JMenuBar menuBar;
    public JMenu ulFile, ulTickets, ulVehicles, ulHelp;
    public JMenuItem liClose, liList, liRegistration, liSale, liAbout,liList1;

    TicketSales(){
        setTitle("Venta de tiquetes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,700,500);

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
        liList1 = new JMenuItem("Consulta");
        ulVehicles.add(liList1);
        //acerca de
        liAbout = new JMenuItem("Acerca de.");
        ulHelp.add(liAbout);

        registrationPane = new JDesktopPane();
        registrationPane.setBounds(20,20, 320, 300);
        registrationPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null ,null));
        registrationPane.setBackground(SystemColor.control);
        contentPane.add(registrationPane);

        lblPassenger = new JLabel("Pasajero");
        lblPassenger.setBounds(50, 20, 80,50);
        registrationPane.add(lblPassenger);

        lblOrigin = new JLabel("Origen");
        lblOrigin.setBounds(50,60, 80,50);
        registrationPane.add(lblOrigin);

        lblDestination = new JLabel("Destino");
        lblDestination.setBounds(50,100,80,50);
        registrationPane.add(lblDestination);

        lblVehicle = new JLabel("Vehiculo");
        lblVehicle.setBounds(50, 140, 80, 50);
        registrationPane.add(lblVehicle);

        lblQuantity = new JLabel("Cantidad");
        lblQuantity.setBounds(50,180, 80, 50);
        registrationPane.add(lblQuantity);


        //inputs
        tfPassenger= new JTextField();
        tfPassenger.setBounds(120,35,150,25);
        registrationPane.add(tfPassenger);

        tfQuantity = new JTextField();
        tfQuantity.setBounds(120,193,150,25);
        registrationPane.add(tfQuantity);

        //JcomboBox
        selectOrigin = new JComboBox();
        selectOrigin.setBounds(120,73,150,25);
        registrationPane.add(selectOrigin);
        selectOrigin.addItem("--Seleccionar--");
        selectOrigin.addItem("Tulua");
        selectOrigin.addItem("Buga");
        selectOrigin.addItem("Cali");

        selectDestination = new JComboBox();
        selectDestination.setBounds(120,113,150,25);
        registrationPane.add(selectDestination);
        selectDestination.addItem("--Seleccionar--");
        selectDestination.addItem("Cali");
        selectDestination.addItem("Tulua");
        selectDestination.addItem("Palmira");
        selectDestination.addItem("Buga");
        selectDestination.addItem("Cerrito");
        selectDestination.addItem("Cartago");
        selectDestination.addItem("Obando");
        selectDestination.addItem("Zarzal");

        selectVehicle = new JComboBox();
        selectVehicle.setBounds(120,153,150,25);
        registrationPane.add(selectVehicle);
        selectVehicle.addItem("--Seleccionar--");
        selectVehicle.addItem("9049");
        selectVehicle.addItem("9086");
        selectVehicle.addItem("9048");

        //Button
        btnSale = new JButton("Venta");
        btnSale.setBounds(10,250,120,25);
        registrationPane.add(btnSale);

        btnClear = new JButton("Limpiar");
        btnClear.setBounds(180,250,120,25);
        registrationPane.add(btnClear);

    }
}