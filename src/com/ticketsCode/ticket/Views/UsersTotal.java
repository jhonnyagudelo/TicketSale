package com.ticketsCode.ticket.Views;

import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;
import com.ticketsCode.ticket.Models.Vo.UsersVO;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UsersTotal extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    UsersTotal frame = new UsersTotal();
                    frame.setVisible(true);
                }catch (Exception e){
                }
            }
        });
    }

    private JPanel contentPane;
    public JButton btnAdd,btnDelete,btnModifity;
    private JScrollPane scroll;
    public  Object[][] data;
    public String[] headBoard;
    public DefaultTableModel dtm;
    public JTable table;


    public UsersTotal (){
        setTitle("Usuarios configuracion");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(340,100,700,300);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);




        btnAdd = new JButton("Agregar");
        btnAdd.setBounds(20,200,100,30);
        contentPane.add(btnAdd);

//        btnModifity = new JButton("Modificar");
//        btnModifity.setBounds(130,200,100,30);
//        contentPane.add(btnModifity);

        btnDelete = new JButton("Eliminar");
        btnDelete.setBounds(240,200,100,30);
        contentPane.add(btnDelete);

        //Tabla
        scroll = new JScrollPane();
        headBoard = new String[] {"Nombres", "Apellidos", "UserName","Tipo","Status","Control", "Conpañia"};
        dtm = new DefaultTableModel(data,headBoard);
        scroll.setBounds(20, 20, 642, 175);

        getContentPane().add(scroll);
        table = new JTable(dtm);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        scroll.setViewportView(table);
        contentPane.add(scroll);

    }
}
