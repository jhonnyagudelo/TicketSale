package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class QrDAO {


    public QrDAO(){

    }
    public boolean QR() {

        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement cs;
        ResultSet rs;
        Vector<Object> row;
        try {

            String SQL = "SELECT * FROM _ticket()";
            cs = conn.getConn().prepareCall(SQL);
            rs = cs.executeQuery();

            while (rs.next()) {
                row = new Vector<Object>();
                row.add(rs.getInt("passenger"));
                row.add(rs.getInt("company"));
                row.add(rs.getInt("destination_code"));
                row.add(rs.getString("license"));
                row.add(rs.getDate("buy"));
                row.add(rs.getTime("hour"));
                System.out.println(row);
            }
        return true;
        } catch (SQLException e) {
            System.out.println("Error al cargar los Datos QR" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar los DATOS QR", "Informacion", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }


}
