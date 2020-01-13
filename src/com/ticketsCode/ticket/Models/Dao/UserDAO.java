package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.*;
import com.ticketsCode.ticket.Views.AddUsers;
import com.ticketsCode.ticket.Views.UsersTotal;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserDAO {
    private AddUsers add;
    private UsersTotal usersTotal;


    public UserDAO(AddUsers add, UsersTotal usersTotal) {
        this.add = add;
        this.usersTotal = usersTotal;
        listCompanies();
        listUSer();
        listControl();
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
        this.add.dcbm.removeAllElements();
        final String SQL = "SELECT * FROM controls";
        try {
            ps = connect.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                add.selectControl.addItem(new ControlsVO(
                       Integer.parseInt(rs.getString("control_id")),
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
                "    ,control\n" +
                "    ) VALUES (\n" +
                "        initcap(?)\n" +
                "        ,initcap(?)\n" +
                "        ,?\n" +
                "        ,?\n" +
                "        ,?\n" +
                "        ,?::category\n" +
                "        ,?\n" +
                "    )";

        try{
            ps = connect.prepareStatement(SQL);
            ps.setString(1,add.getNames());
            ps.setString(2,add.getLast_name());
            ps.setString(3,add.getPassword());
            ps.setInt(4,add.getCompany());
            ps.setString(5,add.getUsername());
            ps.setString(6, add.getType().getCategory());
            ps.setInt(7,add.getControl_id());
            ps.execute();
            return true;
        }catch (SQLException e){
            System.out.println("add Users: " + e.getMessage());
        }catch (Exception e){
            System.out.println("add Users: " + e.getMessage());
        }
        return false;
    }


    public boolean addUser2(UsersVO add) {
        PreparedStatement ps;
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        final String SQL2 = "INSERT INTO users(\n" +
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
                "        ,?::category\n" +
                "    )";

        try{
            ps = connect.prepareStatement(SQL2);
            ps.setString(1,add.getNames());
            ps.setString(2,add.getLast_name());
            ps.setString(3,add.getPassword());
            ps.setInt(4,add.getCompany());
            ps.setString(5,add.getUsername());
            ps.setString(6, add.getType().getCategory());
            ps.execute();
            return true;
        }catch (SQLException e){
            System.out.println("add Users2: " + e.getMessage());
        }catch (Exception e){
            System.out.println("add Users2: " + e.getMessage());
        }
        return false;
    }


    public void listUSer(){
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        ResultSet rs;
        Vector<Object> data;
        PreparedStatement ps;
        for (int i = this.usersTotal.dtm.getRowCount(); i > 0 ; i--) {
            this.usersTotal.dtm.removeRow(i - 1);
        }
        try{

            final String SQL = "SELECT  \n" +
                    "        initcap(u.names)AS name\n" +
                    "        ,initcap(u.last_name)AS Last\n" +
                    "        ,u.username\n" +
                    "        ,u.type_category\n" +
                    "        ,u.status\n" +
                    "        ,initcap(c.name)AS control\n" +
                    "        ,cp.name AS company\n" +
                    "    FROM users u\n" +
                    "        LEFT JOIN controls c\n" +
                    "            ON u.control = c.control_id\n" +
                    "        INNER JOIN companies cp\n" +
                    "            ON u.company = cp.company_id\n" +
                    "    WHERE TRUE;";
            ps = connect.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                data = new Vector<>();
                data.add(rs.getString("name"));
                data.add(rs.getString("Last"));
                data.add(rs.getString("username"));
                data.add(rs.getString("type_category"));
                data.add(rs.getString("status"));
                data.add(rs.getString("control"));
                data.add(rs.getString("company"));
                this.usersTotal.dtm.addRow(data);

            }

        }catch (SQLException e){
            System.out.println("load users:" + e.getMessage());

        }
    }


    public boolean delete(UsersVO user) {
        PreparedStatement ps;
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();

        String SQL = "DELETE FROM users WHERE username = ?";
        try {
            ps = connect.prepareStatement(SQL);
            ps.setString(1, user.getUsername());
            ps.execute();
            return true;
        } catch (SQLException e1) {
            System.out.println("Error delete " + e1.getMessage());
        }
        return false;
    }

}
