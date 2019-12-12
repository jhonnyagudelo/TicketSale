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
                row.add(String.valueOf(rs.getDate("buy")));
                row.add(String.valueOf(rs.getTime("hour")));
                System.out.println("QR: "+ row);

            }

        } catch (SQLException e) {
            System.out.println("Error al cargar los Datos QR" + e.getMessage());
            System.out.println("String" + row.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar los DATOS QR", "Informacion", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            System.out.println("Error Datos QR" + e.getMessage());
        }
        return row.toString();
    }

    }
    
