package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.UsersVO;
import com.ticketsCode.ticket.Views.TicketSales;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private UsersVO usersVO;

    public UserDAO(UsersVO userVO){
        this.usersVO = userVO;
    }

    public boolean authUser(UsersVO usersVO){
        boolean auth = false;
        PreparedStatement st = null;
        DataBaseConnection conn = new DataBaseConnection();
        ResultSet rs;
        Connection connect = conn.getConn();
        String SQL = "SELECT * FROM users WHERE TRUE AND username = ? AND password = ? AND status = 't'";
        try{
            st = connect.prepareStatement(SQL);
            st.setString(1,usersVO.getUsername());
            st.setString(2,usersVO.getPassword().toString());
            rs = st.executeQuery();
            while (rs.next()){
                if(rs.getString("type_category").equals("Administrador")){
                    JOptionPane.showMessageDialog(null,"Bienvenido admin" + rs.getString("names"));

                    new TicketSales().setVisible(true);
                    auth = true;
                } else if (rs.getString("type_category").equals("Control")){
                    JOptionPane.showMessageDialog(null,"Bienvenido control" + rs.getString("names"));
                    new TicketSales().setVisible(true);
                    auth = true;
                }
            }if(auth = false){
                JOptionPane.showMessageDialog(null,"Usuario no existe o fue desactivado");
                usersVO.setPassword("");
                usersVO.setUsername("");
            }
        }catch (Exception e){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);;
            System.out.println("Login: " + e);
        }

        return auth;
    }


}
