package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.CompanyVO;
import com.ticketsCode.ticket.Models.Vo.DataExport;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.SearchVehicle;
import com.ticketsCode.ticket.Views.TotalTravel;
import com.ticketsCode.ticket.Views.TravelHistory;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class SearchDAO {
    private SearchVehicle search;
    private TravelHistory travelHistory;
    private TotalTravel totalTravel;


    public SearchDAO() {

    }

    public SearchDAO(SearchVehicle search, TravelHistory travelHistory, TotalTravel totalTravel) {
        this.search = search;
        this.totalTravel = totalTravel;
        this.travelHistory = travelHistory;
        listCompanies();
    }

    public boolean search(VehicleVO autoBusVO) {
        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement cs;
        ResultSet rs;
        Vector<Object> row;
        for (int i = this.search.dtm.getRowCount(); i > 0; i--) {
            this.search.dtm.removeRow(i - 1);
        }
        try {

            String query = "SELECT * FROM getinfo(?)";
            cs = conn.getConn().prepareCall(query);
            cs.setInt(1, autoBusVO.getInternal_number());
            rs = cs.executeQuery();

            while (rs.next()) {
                row = new Vector<>();
                row.add(rs.getInt("internal_number"));
                row.add(rs.getString("license"));
                row.add(rs.getInt("capacity"));
                row.add(rs.getString("company"));
                row.add(rs.getString("active"));
                this.search.dtm.addRow(row);

            }

        } catch (SQLException e) {
            System.out.println("Error al cargar los Datos" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar los DATOS", "Informacion", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }



    public void listCompanies() {
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        PreparedStatement ps;
        ResultSet rs;
        this.totalTravel.dcbm.removeAllElements();
        try {
            String SQL = "SELECT company_id, name, nit FROM companies ORDER BY company_id";
            ps = connect.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalTravel.selectCompany.addItem(new CompanyVO(
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





    public boolean travel_history(DataExport data) {
        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement cs;
        ResultSet rs;
        Vector<Object> list;
        try {
            String SQL = "SELECT * FROM travel_history(?,?,?)";
            cs = conn.getConn().prepareCall(SQL);
            cs.setDate(1, (data.getDateStart()));
            cs.setDate(2, (data.getDateEnd()));
            cs.setInt(3, data.getVehicle());
            rs = cs.executeQuery();
            while (rs.next()) {
                list = new Vector<>();
                list.add(rs.getInt("internal_number"));
                list.add(rs.getString("name"));
                list.add((rs.getInt("total_tickets")));
                list.add(rs.getDate("buy"));
                System.out.println("Vector historia: " + list);
            }
        } catch (SQLException e1) {
            System.out.println("travel rs: " + e1.getMessage());
        } catch (Exception e1) {
            System.out.println("travelSQL rs: " + e1.getMessage());

        }
        return false;
    }






}