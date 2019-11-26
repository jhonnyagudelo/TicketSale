package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.CompanyVO;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.ListVehicle;
import java.sql.ResultSet;
import javax.swing.*;
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
        listCompanies();

    }


    public boolean vehiculeRecorder(VehicleVO autoBusVO){
        PreparedStatement st = null;
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        String SQL = "INSERT INTO vehicles(internal_number, license, capacity, company) VALUES (?,?,?,?)";
        try{
            st = connect.prepareStatement(SQL);
            st.setInt(1,autoBusVO.getInternal_number());
            st.setString(2,autoBusVO.getLicense());
            st.setInt(3,autoBusVO.getCapacity());
            st.setInt(4,autoBusVO.getCompany());
            st.execute();
            return  true;
        } catch (Exception e1){
            System.out.println("Error SQL "+ e1.getMessage());
        }
        return false;
    }


//    public boolean vehiculeRecorder(VehicleVO myVehicle) {
//        DataBaseConnection conn;
//        conn = new DataBaseConnection();
//        try {
//            Statement statute = conn.getConn().createStatement();
//            statute.executeUpdate("INSERT INTO vehicles VALUES('"+ myVehicle.getInternal_number()+"','"+ myVehicle.getLicense()+"','"+ myVehicle.getCapacity()+"','"+ myVehicle.getCompany()+"')");
//            JOptionPane.showMessageDialog(null,"Se ha registrado Correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
//            statute.close();
//            conn.disconnect();
//            return true;
//
//        }catch(SQLException e) {
//            System.out.println(e.getMessage());
//            JOptionPane.showMessageDialog(null, "No se registro");
//        }
//
//        return false;
//    }



    public void listCompanies(){
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        PreparedStatement ps;
        ResultSet rs;
        this.autoBus.dcbm.removeAllElements();
        try{
            String SQL = "SELECT company_id, name, nit FROM companies ORDER BY company_id";
            ps = connect.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()){
                autoBus.selectCompany.addItem(new CompanyVO(
                        Integer.parseInt(rs.getString("company_id")),
                        rs.getString("name"),
                        rs.getInt("nit")));
            }

        }catch (SQLException e1){
            System.out.println("Error en la lista company " + e1.getMessage() );
        }catch (Exception e1){
            System.out.println(e1.getMessage());
        }
    }



    public void _loadTable(){

        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement cs;
        ResultSet rs;
        Vector<Object>row;
        for (int i = this.autoBus.dtm.getRowCount(); i > 0; i--){
            this.autoBus.dtm.removeRow(i - 1);
        }
        try{

            String query = "SELECT * FROM getinfot()";
            cs = conn.getConn().prepareCall(query);
            rs = cs.executeQuery();

            while (rs.next()){
                row = new Vector<Object>();
                row.add(rs.getInt("internal_number"));
                row.add(rs.getString("license"));
                row.add(rs.getInt("capacity"));
                row.add(rs.getString("company"));
                row.add(rs.getBoolean("active"));
                this.autoBus.dtm.addRow(row);

            }

        } catch (SQLException e) {
            System.out.println("Error al cargar los Datos" + e.getMessage());
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
