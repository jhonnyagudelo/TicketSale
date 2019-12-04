package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.VehicleVO;
import com.ticketsCode.ticket.Views.SearchVehicle;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class SearchDAO {
    private SearchVehicle search;

    public SearchDAO(SearchVehicle search) {
        this.search = search;
    }

    public boolean search(VehicleVO autoBusVO){
        DataBaseConnection conn = new DataBaseConnection();
        CallableStatement cs;
        ResultSet rs;
        Vector<Object>row;
        try{

            String query = "SELECT * FROM getinfo(?)";
            cs = conn.getConn().prepareCall(query);
            cs.setInt(1,autoBusVO.getInternal_number());
            rs = cs.executeQuery();

            while (rs.next()){
                row = new Vector<Object>();
                row.add(rs.getInt("internal_number"));
                row.add(rs.getString("license"));
                row.add(rs.getInt("capacity"));
                row.add(rs.getString("company"));
                row.add(rs.getString("active"));
                this.search.dtm.addRow(row);

            }

        } catch (SQLException e) {
            System.out.println("Error al cargar los Datos" + e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al cargar los DATOS","Informacion",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }


//
//    public boolean search(VehicleVO autoBusVO){
//        DataBaseConnection conn = new DataBaseConnection();
//        Connection connection = conn.getConn();
//        PreparedStatement ps;
//        ResultSet rs;
//        String SQL = "SELECT * FROM vehicles WHERE internal_number = ?";
//
//        try{
//            ps = connection.prepareStatement(SQL);
//            ps.setInt(1,autoBusVO.getInternal_number());
//            rs = ps.executeQuery();
//            while (rs.next()){
//                autoBusVO.setVehicle_id(Integer.parseInt(rs.getString("vehicle_id")));
//                autoBusVO.setInternal_number(Integer.parseInt(rs.getString("internal_number")));
//                autoBusVO.setLicense(rs.getString("license"));
//                autoBusVO.setCapacity(Integer.parseInt(rs.getString("capacity")));
//                autoBusVO.setCompany(Integer.parseInt(rs.getString("company")));
//                return true;
//            }
//
//        }catch (SQLException e1){
//            System.out.printf("error searh: " + e1.getMessage());
//        }catch (Exception e1){
//            System.out.printf("eeeer: " +e1.getMessage());
//        }
//        return false;
//    }

//    public boolean search(VehicleVO autoBusVO){
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        DataBaseConnection conn = new DataBaseConnection();
//        Connection connect = conn.getConn();
//        String SQL = "SELECT * FROM vehicles WHERE internal_number = ?";
//        try {
//            ps = connect.prepareStatement(SQL);
//            ps.setInt(1,autoBusVO.getInternal_number());
//            rs = ps.executeQuery();
//            if(rs.next()){
//                autoBusVO.setVehicle_id(Integer.parseInt(rs.getString("vehicle_id")));
//                autoBusVO.setInternal_number(Integer.parseInt(rs.getString("internal_number")));
//                autoBusVO.setLicense(rs.getString("license"));
//                autoBusVO.setCapacity(Integer.parseInt(rs.getString("capacity")));
//                return true;
//            }
//        }catch (SQLException e1){
//            System.out.printf("errorrrrr: " + e1.getMessage());
//        }catch (Exception e1){
//            System.out.printf("eeeeee"+ e1.getMessage());
//        }
//        return false;
//    }

}