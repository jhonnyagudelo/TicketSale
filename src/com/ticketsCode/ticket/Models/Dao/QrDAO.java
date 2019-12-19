package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.DataQr;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class QrDAO {

    public QrDAO() {

    }

    public String QR() {

        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement cs;
        ResultSet rs;
        Vector<String> row = null;
        try {

            String SQL = "SELECT * FROM _ticket()";
            cs = conn.getConn().prepareCall(SQL);
            rs = cs.executeQuery();

            while (rs.next()) {
                row = new Vector<String>();
                row.add(String.valueOf(rs.getInt("passenger")));
                row.add(String.valueOf(rs.getInt("company")));
                row.add(String.valueOf(rs.getInt("destination_code")));
                row.add(rs.getString("license"));
                row.add(rs.getString("buy"));
                row.add(String.valueOf(rs.getTime("hour")));
                System.out.println("QR: " + row);

            }

        } catch (SQLException e) {
            System.out.println("Error al cargar los Datos QR" + e.getMessage());
            System.out.println("String" + row.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar los DATOS QR: ", "Informacion", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.out.println("Error Datos QR" + e.getMessage());
        }
        return String.format(row.toString());
    }

    public DataQr dateTickect() {
        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement  cs;
        ResultSet rs;
        DataQr dataQr = null;
        String SQL = "SELECT * FROM dateticket()";
        try {
            cs = conn.getConn().prepareCall(SQL);
            rs = cs.executeQuery();
            while (rs.next()){
                dataQr = new DataQr();
                dataQr.setCompany(rs.getString("company"));
                dataQr.setDestiny(rs.getString("destiny"));
                System.out.println("hola: " + dataQr);
            }
        }catch (Exception e){
            System.out.println("datos tickets: " + e.getMessage());
        }
        return dataQr;
    }

}
    
