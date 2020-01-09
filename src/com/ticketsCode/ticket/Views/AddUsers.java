package com.ticketsCode.ticket.Views;

import com.ticketsCode.ticket.Models.Vo.Category;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class AddUsers extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    AddUsers frame = new AddUsers();
                    frame.setVisible(true);
                }catch (Exception e){
                   e.printStackTrace();
                }
            }
        });
    }


    private JPanel contentPane;
    private JDesktopPane registerUser, generalData;
    public JTextField tfUsername, tfName, tfLastName;
    public JPasswordField tfPassword, tfConfiPassword;
    public JButton btnAdd, btnCancel;
    public JComboBox<Category> selectType;
    public JComboBox selectControl,selecCompany;
    public DefaultComboBoxModel dcbm, dcbmC, dcbmT;
    public JRadioButton rAdmin,rControl;
    public ButtonGroup type;
    public JLabel lblUsername, lblPassword, lblConfiPassword, lblName, lblLastName, lblType, lblControl, lblCompany;
    private JMenuBar menuBar;
    public JMenu ulFile, ulTickets, ulVehicles, ulHelp;
    public JMenuItem liClose, liList, liRegistration, liSale, liAbout,liList1,liDelete,liExport;

    public AddUsers(){
        setTitle("Agregar usuarios");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(500,250,540,450);

        contentPane = new JPanel();
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

        liExport = new JMenuItem("Historial vehicular");
        ulTickets.add(liExport);

        //item menu vehiculos
        liRegistration = new JMenuItem("Registro1");
        ulVehicles.add(liRegistration);

        liList1 = new JMenuItem("Consulta");
        ulVehicles.add(liList1);
        //acerca de
        liAbout = new JMenuItem("Acerca de.");
        ulHelp.add(liAbout);

        //Formulario de usuarios

        registerUser = new JDesktopPane();
        registerUser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,null));
        registerUser.setBackground(SystemColor.control);
        registerUser.setBounds(20,20,480,150);
        registerUser.setBorder(BorderFactory.createTitledBorder("Registro de usuario"));
        contentPane.add(registerUser);

        lblUsername = new JLabel("UserName: ");
        lblUsername.setBounds(10,10,100,80);
        registerUser.add(lblUsername);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(200,10,100,80);
        registerUser.add(lblPassword);

        lblConfiPassword = new JLabel("Confirmar password:");
        lblConfiPassword.setBounds(143,50,130,80);
        registerUser.add(lblConfiPassword);

        tfUsername = new JTextField();
        tfUsername.setBounds(80,40,100,21);
        registerUser.add(tfUsername);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(270,40,100,21);
        registerUser.add(tfPassword);

        tfConfiPassword = new JPasswordField();
        tfConfiPassword.setBounds(270,80,100,21);
        registerUser.add(tfConfiPassword);

        generalData = new JDesktopPane();
        generalData.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        generalData.setBackground(SystemColor.control);
        generalData.setBorder(BorderFactory.createTitledBorder("Datos generales"));
        generalData.setBounds(20,180,480,150);
        contentPane.add(generalData);

        lblName = new JLabel("Nombres: ");
        lblName.setBounds(50,10,100,80);
        generalData.add(lblName);

        lblLastName = new JLabel("Apellidos:");
        lblLastName.setBounds(240,10,100,80);
        generalData.add(lblLastName);

        lblCompany = new JLabel("Compañia:");
        lblCompany.setBounds(40,50,100,80);
        generalData.add(lblCompany);

        lblControl = new JLabel("Tipo:");
        lblControl.setBounds(230,50,100,80);
        generalData.add(lblControl);

        lblType = new JLabel("Control:");
        lblType.setBounds(360,50,100,80);
        generalData.add(lblType);

        tfName = new JTextField();
        tfName.setBounds(110,40,110,21);
        generalData.add(tfName);

        tfLastName = new JTextField();
        tfLastName.setBounds(300,40,110,21);
        generalData.add(tfLastName);

        selecCompany = new JComboBox();
        selecCompany.setBounds(10,100,140,21);
        dcbmC = new DefaultComboBoxModel();
        selecCompany.addItem("Elige una opción");
        generalData.add(selecCompany);

        selectType = new JComboBox();
        selectType.setBounds(170,100,140,21);
//        dcbmT = new ;
        selectType.setModel( new DefaultComboBoxModel(Category.values()));
        generalData.add(selectType);

//        rAdmin = new JRadioButton("Administrador");
//        rAdmin.setBounds(170,100,140,21);
//        generalData.add(rAdmin);
//
//        rControl = new JRadioButton("Control",true);
//        rControl.setBounds(170,120,140,21);
//        generalData.add(rControl);

//        type = new ButtonGroup();
//        type.add(rAdmin);
//        type.add(rControl);

        selectControl = new JComboBox();
        selectControl.setEnabled(false);
        selectControl.setBounds(320,100,140,21);
        dcbm = new DefaultComboBoxModel();
        selectControl.addItem("Elige una opción");
        generalData.add(selectControl);

        btnAdd = new JButton("Guardar");
        btnAdd.setBounds(22,340,80,30);
        contentPane.add(btnAdd);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(108,340,80,30);
        contentPane.add(btnCancel);

    }
}
