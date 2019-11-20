package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.ListVehicle;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Vector;

public class VehicleDAO implements ActionListener, MouseListener {
    private ListVehicle autoBus;


    //CONSTRUCTOR
    public VehicleDAO(ListVehicle autoBus){
        this.autoBus = autoBus;
        _loadTable();
        ListCompanies();

    }

    public void vehiculeRecorder(VehicleVO myVehicle) {
        DataBaseConnection conn;
        conn = new DataBaseConnection();
        try {
            Statement statute = conn.getConn().createStatement();
            statute.executeUpdate("INSERT INTO vehicles VALUES('"+ myVehicle.getInternal_number()+"','"+ myVehicle.getLicense()+"','"+ myVehicle.getCapacity()+"','"+ myVehicle.getCompany()+"')");
            JOptionPane.showMessageDialog(null,"Se ha registrado Correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
            statute.close();
            conn.disconnect();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se registro");
        }

    }

    private void clear(){
       this.autoBus.tfInternalNumber.setText("");
       this.autoBus.tfLicense.setText("");
       this.autoBus.tfCapacity.setText("");
    }

    public void ListCompanies(){
        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement cs;
        ResultSet rs;
        Vector<Object>position;
        this.autoBus.dcbm.removeAllElements();
        try {
            String SQL = "SELECT name FROM companies";

            this.autoBus.dcbm.addElement("Elige una opción");
            this.autoBus.selectCompany.setModel(this.autoBus.dcbm);
            cs = conn.getConn().prepareCall(SQL);
            rs = cs.executeQuery();
            while (rs.next()){
                position = new Vector<Object>();
                position.add(rs.getString("name"));
                this.autoBus.dcbm.addElement(position);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error");
        }
    }




    protected void _loadTable(){

        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement cs;
        ResultSet rs;
        Vector<Object>row;
        for (int i = this.autoBus.dtm.getRowCount(); i > 0; i--){
            this.autoBus.dtm.removeRow(i - 1);
        }
        try{

            String SQL = "SELECT internal_number,license,capacity,company,active FROM vehicles";
            cs = conn.getConn().prepareCall(SQL);
            rs = cs.executeQuery();

            while (rs.next()){
                row = new Vector<Object>();
                row.add(rs.getInt("internal_number"));
                row.add(rs.getString("license").toUpperCase().trim());
                row.add(rs.getInt("capacity"));
                row.add(rs.getInt("company"));
                row.add(rs.getBoolean("active"));
                this.autoBus.dtm.addRow(row);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error al cargar los Datos");
            JOptionPane.showMessageDialog(null,"Error al cargar los DATOS","Informacion",JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
             DataBaseConnection conn = new DataBaseConnection();
            CallableStatement cs;
            ResultSet rs;
            int pulseRow = this.autoBus.table.getSelectedRow();
            if(pulseRow > 0){
                int identifier = (int)this.autoBus.dtm.getValueAt(pulseRow, 0);
                try{
                    cs = conn.getConn().prepareCall("{SELECT getinfo}");
                    cs.setInt(1,identifier);
                    rs = cs.executeQuery();
                    if(rs.next()){
                        this.autoBus.tfInternalNumber.setText(String.valueOf(rs.getInt(1)));
                        this.autoBus.tfLicense.setText(String.valueOf(rs.getInt(2)));
                        this.autoBus.tfCapacity.setText(rs.getString(3));
                        this.autoBus.tfCompany.setText(rs.getString(4));
                    }
                } catch (SQLException e1){
                    System.out.println("Error al cargar los CLIENTES");
                    System.out.println(e1.getMessage());
                    JOptionPane.showMessageDialog(null,"Error al cargar los CLIENTES","Informacion",JOptionPane.INFORMATION_MESSAGE);
                }
            }
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
