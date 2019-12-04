package com.ticketsCode.ticket.Views;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListVehicle extends JFrame  {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListVehicle frame = new ListVehicle();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




    private JPanel contentPane;
    public JDesktopPane registrationPane;
    private JLabel lblInternalNumber, lblLicense, lblCapacity,lblCompany,lblTitle;
    public JTextField tfInternalNumber, tfLicense, tfCapacity, tfCompany;
    public JComboBox selectCompany;
    public JButton btnSave, btnClear,btnDelete,btnUpdate, bntSearch;
    private JMenuBar menuBar;
    public JMenu ulFile, ulTickets, ulVehicles, ulHelp;
    public JMenuItem liClose, liList, liRegistration, liSale, liAbout,liList1,liDelete;
    private JScrollPane scroll;
    public  Object[][] data;
    public String[] headBoard;
    public DefaultTableModel dtm;
    public DefaultComboBoxModel dcbm;
    public JTable table;

  

    public ListVehicle(){
        setTitle("Registro de vehiculos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200,100,700,600);

        contentPane = new JPanel();
        //getContentPane().add(contentPane);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Barra Menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Menu
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
        liRegistration = new JMenuItem("Registro");
        ulVehicles.add(liRegistration);

        liDelete = new JMenuItem("Eliminar");
        ulVehicles.add(liDelete);

        liList1 = new JMenuItem("Consulta");
        ulVehicles.add(liList1);
            //acerca de
        liAbout = new JMenuItem("Acerca de.");
        ulHelp.add(liAbout);

        //Formulario de entrada de vehiculos
        registrationPane = new JDesktopPane();
        registrationPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        registrationPane.setBackground(SystemColor.control);
        registrationPane.setBounds(117, 68, 437, 236);
        contentPane.add(registrationPane);
        registrationPane.setLayout(null);

        //label
        lblInternalNumber = new JLabel("Numero interno:");
        lblInternalNumber.setBounds(61,62,100,14);
        registrationPane.add(lblInternalNumber);

        lblLicense = new JLabel("Numero placa:");
        lblLicense.setBounds(61,96,100,14);
        registrationPane.add(lblLicense);

        lblCapacity = new JLabel("Cap. vehicular");
        lblCapacity.setBounds(61,135,   100,14);
        registrationPane.add(lblCapacity);

        lblCompany = new JLabel("Compañia");
        lblCompany.setBounds(61,165,100,14);
        registrationPane.add(lblCompany);

        //Cuadro de texto
        tfInternalNumber = new JTextField();
        tfInternalNumber.setBounds(160,57,200,25);
        registrationPane.add(tfInternalNumber);
        tfInternalNumber.setColumns(1);

        tfLicense = new JTextField();
        tfLicense.setBounds(160,91,200,25);
        registrationPane.add(tfLicense);
        tfLicense.setColumns(1);

        tfCapacity = new JTextField();
        tfCapacity.setBounds(160,132,200,25);
        registrationPane.add(tfCapacity);
        tfCapacity.setColumns(1);

        selectCompany = new JComboBox();
        selectCompany.setBounds(160,164,200,25);
        dcbm = new DefaultComboBoxModel();
        registrationPane.add(selectCompany);
        selectCompany.addItem("Elige una opción");
//        selectCompany.addItem("Coodetrans");
//        selectCompany.addItem("TransUnidos");

        //Botones
        btnSave = new JButton("Guardar");
        btnSave.setBounds(217,202,89,23);
        registrationPane.add(btnSave);

        btnClear = new JButton("Limpiar");
        btnClear.setBounds(316,202,89,23);
        registrationPane.add(btnClear);


        //Tabla
        scroll = new JScrollPane();
        headBoard = new String[] {"Numero interno", "Placa", "Capacidad", "Compañia", "Activo"};
        dtm = new DefaultTableModel(data,headBoard);
        scroll.setBounds(46, 315, 593, 125);

        getContentPane().add(scroll);
        table = new JTable(dtm);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        scroll.setViewportView(table);
        contentPane.add(scroll);

        // botones tabla
        btnUpdate = new JButton("Actualizar");
        btnUpdate.setBounds(162, 451, 100, 23);
        contentPane.add(btnUpdate);

        btnDelete = new JButton("Eliminar");
        btnDelete.setBounds(419, 451, 100, 23);
        contentPane.add(btnDelete);


        setVisible(true);

    }

}
