package com.ticketsCode.ticket.Views;

import com.ticketsCode.ticket.Models.Vo.Category;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class EditUser extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    EditUser frame = new EditUser();
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


    public EditUser(){
        setTitle("Agregar usuarios");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(500,250,540,450);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);



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
        selectType.setModel( new DefaultComboBoxModel(Category.values()));
        generalData.add(selectType);



        selectControl = new JComboBox();
//        selectControl.setEnabled(false);
        selectControl.setBounds(320,100,140,21);
        dcbm = new DefaultComboBoxModel();
        selectControl.addItem("Elige una opción");
        generalData.add(selectControl);

        btnAdd = new JButton("Actualizar");
        btnAdd.setBounds(22,340,100,30);
        contentPane.add(btnAdd);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(128,340,100,30);
        contentPane.add(btnCancel);

    }
}
