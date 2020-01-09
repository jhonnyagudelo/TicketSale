package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.CompanyVO;
import com.ticketsCode.ticket.Models.Vo.ControlsVO;
import com.ticketsCode.ticket.Models.Vo.UsersVO;
import com.ticketsCode.ticket.Views.AddUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private AddUsers add;


    public UserDAO(AddUsers add) {
        this.add = add;
        listCompanies();
    }

    public void listCompanies() {
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        PreparedStatement ps;
        ResultSet rs;
        this.add.dcbmC.removeAllElements();
        try {
            String SQL = "SELECT company_id, name, nit FROM companies ORDER BY company_id";
            ps = connect.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                add.selecCompany.addItem(new CompanyVO(
                        Integer.parseInt(rs.getString("company_id")),
                        rs.getString("name"),
                        rs.getInt("nit")));
                System.out.println("lista company: " + rs.toString());
            }
        } catch (SQLException e1) {
            System.out.println("Error en la lista company " + e1.getMessage());
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void listControl() {
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        PreparedStatement ps;
        ResultSet rs;
        this.add.selectControl.removeAllItems();
        final String SQL = "SELECT code, name FROM controls";
        try {
            ps = connect.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                add.selecCompany.addItem(new ControlsVO(
                        rs.getString("code"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("Error Control: " + e.getMessage());

        }
    }

    public boolean addUser(UsersVO add) {
        PreparedStatement ps;
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        final String SQL = "INSERT INTO users(\n" +
                "    names\n" +
                "    ,last_name\n" +
                "    ,password\n" +
                "    ,company\n" +
                "    ,username\n" +
                "    ,type_category\n" +
                "    ) VALUES (\n" +
                "        initcap(?)\n" +
                "        ,initcap(?)\n" +
                "        ,?\n" +
                "        ,?\n" +
                "        ,?\n" +
                "        ,?\n" +
                "    )";
        try{
            ps = connect.prepareStatement(SQL);
            ps.setString(1,add.getNames());
            ps.setString(2,add.getLast_name());
            ps.setString(3,add.getPassword());
            ps.setInt(4,add.getCompany());
            ps.setString(5,add.getUsername());
            ps.setObject(6, add.getType());
            ps.execute();
            System.out.println("SQL users: " + ps.execute());
            return true;
        }catch (SQLException e){
            System.out.println("add Users: " + e.getMessage());
        }catch (Exception e){
            System.out.println("add Users: " + e.getMessage());
        }
        return false;
    }

}
