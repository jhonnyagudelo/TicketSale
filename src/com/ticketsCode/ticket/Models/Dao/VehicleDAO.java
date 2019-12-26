package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.CompanyVO;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.ListVehicle;
import com.ticketsCode.ticket.Views.SearchVehicle;


import java.sql.ResultSet;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Vector;

public class VehicleDAO {
    private ListVehicle autoBus;


    //CONSTRUCTOR
    public VehicleDAO(ListVehicle autoBus) {
        this.autoBus = autoBus;
        _loadTable();
        listCompanies();
    }


    /**
     * Metodo que inserta los vehiculos a la BD
     *
     * @param autoBusVO son los datos de la DB
     * @return retorna si es verdadero o falso
     */
    public boolean vehiculeRecorder(VehicleVO autoBusVO) {
        PreparedStatement st = null;
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        String SQL = "INSERT INTO vehicles(internal_number, license, capacity, company) VALUES (?,UPPER(REPLACE(?,'',' ')),?,?)";
        try {
            st = connect.prepareStatement(SQL);
            st.setInt(1, autoBusVO.getInternal_number());
            st.setString(2, autoBusVO.getLicense());
            st.setInt(3, autoBusVO.getCapacity());
            st.setInt(4, autoBusVO.getCompany());
            st.execute();
            return true;
        } catch (Exception e1) {
            System.out.println("Error SQL " + e1.getMessage());
        }
        return false;
    }


    /**
     * JComboBox de company
     */

    public void listCompanies() {
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        PreparedStatement ps;
        ResultSet rs;
        for (int i = this.autoBus.dtm.getRowCount(); i > 0; i--) {
            this.autoBus.dtm.removeRow(i - 1);
        }
        try {
            String SQL = "SELECT company_id, name, nit FROM companies ORDER BY company_id";
            ps = connect.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                autoBus.selectCompany.addItem(new CompanyVO(
                        Integer.parseInt(rs.getString("company_id")),
                        rs.getString("name"),
                        rs.getInt("nit")));
            }

        } catch (SQLException e1) {
            System.out.println("Error en la lista company " + e1.getMessage());
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    /**
     * recarga la JTable
     */

    public void _loadTable() {

        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement cs;
        ResultSet rs;
        Vector<Object> row;
        for (int i = this.autoBus.dtm.getRowCount(); i > 0; i--) {
            this.autoBus.dtm.removeRow(i - 1);
        }
        try {

            String query = "SELECT * FROM getinfot()";
            cs = conn.getConn().prepareCall(query);
            rs = cs.executeQuery();

            while (rs.next()) {
                row = new Vector<Object>();
                row.add(rs.getInt("internal_number"));
                row.add(rs.getString("license"));
                row.add(rs.getInt("capacity"));
                row.add(rs.getString("company"));
                row.add(rs.getBoolean("active"));
                this.autoBus.dtm.addRow(row);
                System.out.println(row.toString());

            }

        } catch (SQLException e) {
            System.out.println("Error al cargar los Datos" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar los DATOS", "Informacion", JOptionPane.ERROR_MESSAGE);
        }
    }


    public boolean Modifity(VehicleVO autoBusVO) {
        PreparedStatement st = null;
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        String SQL = "UPDATE vehicles SET internal_number= ?, license = ?, capacity = ?, active = ?";
        try {
            st = connect.prepareStatement(SQL);
            st.setInt(1, autoBusVO.getInternal_number());
            st.setString(2, autoBusVO.getLicense());
            st.setInt(3, autoBusVO.getCapacity());
            st.setBoolean(4, autoBusVO.getActive());
            st.execute();
            return true;
        } catch (Exception e1) {
            System.out.println("Error Update " + e1.getMessage());
        }
        return false;
    }


    public boolean delete(VehicleVO autoBusVO) {
        PreparedStatement ps;
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();

        String SQL = "DELETE FROM vehicles WHERE internal_number = ?";
        try {
            ps = connect.prepareStatement(SQL);
            ps.setInt(1, autoBusVO.getInternal_number());
            ps.execute();
            return true;
        } catch (SQLException e1) {
            System.out.println("Error delete " + e1.getMessage());
        }
        return false;
    }
}
