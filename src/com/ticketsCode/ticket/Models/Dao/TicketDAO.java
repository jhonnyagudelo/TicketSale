package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.CompanyVO;
import com.ticketsCode.ticket.Models.Vo.DestinationsVO;
import com.ticketsCode.ticket.Models.Vo.OriginsVO;
import com.ticketsCode.ticket.Views.TicketSales;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class TicketDAO {

    private TicketSales tickSale;

    public TicketDAO ( TicketSales tickSale){
        this.tickSale = tickSale;
        _loadTableSale();
        listDestiny();
        listVehicle();
        listOrigin();
    }

    public void listDestiny() {
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        PreparedStatement ps;
        ResultSet rs;
        this.tickSale.dcbmD.removeAllElements();
        try {
            String SQL = "SELECT destination_id, name FROM destinations ORDER BY destination_id";
            ps = connect.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()){
                tickSale.selectDestination.addItem(new DestinationsVO(
                        Integer.parseInt(rs.getString("destination_id")),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.printf("Error SQL Destiny "+ e.getMessage());
        } catch (Exception e){
            System.out.printf("Error Destiny "+ e.getMessage());
        }
    }

    public void listVehicle() {
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        PreparedStatement
    }

    public void listOrigin(){
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        PreparedStatement ps;
        ResultSet rs;
        this.tickSale.dcbm.removeAllElements();
        try{
            String SQL = "SELECT origin_id, name FROM origins ORDER BY origin_id";
            ps = connect.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()){
                tickSale.selectOrigin.addItem(new OriginsVO(
                        Integer.parseInt(rs.getString("origin_id")),
                        rs.getString("name")));
            }

        }catch (SQLException e1){
            System.out.println("Error en la lista origin " + e1.getMessage() );
        }catch (Exception e1){
            System.out.println(e1.getMessage());
        }
    }

// cargar ventas;
public void _loadTableSale(){

    DataBaseConnection conn = new DataBaseConnection();
    CallableStatement cs;
    ResultSet rs;
    Vector<Object> row;
    for (int i = this.tickSale.dtm.getRowCount(); i > 0; i--){
        this.tickSale.dtm.removeRow(i - 1);
    }
    try{

        String query = "SELECT * FROM sale()";
        cs = conn.getConn().prepareCall(query);
        rs = cs.executeQuery();

        while (rs.next()){
            row = new Vector<Object>();
            row.add(rs.getInt("passenger"));
            row.add(rs.getString("origin"));
            row.add(rs.getString("destiny"));
            row.add(rs.getString("license"));
            row.add(rs.getInt("quantity"));
            row.add(rs.getDate("buy"));
            this.tickSale.dtm.addRow(row);

        }

    } catch (SQLException e) {
        System.out.println("Error al cargar los Datos" + e.getMessage());
        JOptionPane.showMessageDialog(null,"Error al cargar los DATOS","Informacion",JOptionPane.ERROR_MESSAGE);
    }
}




}
