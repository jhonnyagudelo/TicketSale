package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.LoginVO;
import com.ticketsCode.ticket.Models.Vo.UsersVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private LoginVO loginVO;

    public UserDAO(LoginVO loginVO) {
        this.loginVO = loginVO;
    }

    public LoginVO authUser() {
        PreparedStatement st = null;
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        ResultSet rs;
        String SQL = "SELECT * FROM users WHERE TRUE AND username = ? AND password = ? AND status = 't'";
        try {
            st = connect.prepareStatement(SQL);
            st.setString(1, loginVO.getUsername());
            st.setString(2, loginVO.getPassword().toString());
            st.executeQuery();
            System.out.println("user: " + loginVO);
        } catch (Exception e) {
            System.out.println("User: " + e.getMessage());
        }


        return null;
    }


//    public boolean authUser(UsersVO usersVO){
//        boolean auth = false;
////        String password = String.valueOf()
//        PreparedStatement st = null;
//        DataBaseConnection conn = new DataBaseConnection();
//        ResultSet rs;
//        Connection connect = conn.getConn();
//        String SQL = "SELECT * FROM users WHERE TRUE AND username = ? AND password = ? AND status = 't'";
//        try{
//            st = connect.prepareStatement(SQL);
//            st.setString(1,usersVO.getUsername());
//            st.setString(2,usersVO.getPassword().toString());
//            rs = st.executeQuery();
//            while (rs.next()){
//                if(rs.getString("type_category").equals("Administrador")){
//                    JOptionPane.showMessageDialog(null,"Bienvenido admin" + rs.getString("names"));
//
//                    new TicketSales().setVisible(true);
//                    auth = true;
//                } else if (rs.getString("type_category").equals("Control")){
//                    JOptionPane.showMessageDialog(null,"Bienvenido control" + rs.getString("names"));
//                    new TicketSales().setVisible(true);
//                    auth = true;
//                }
//            }if(auth = false){
//                JOptionPane.showMessageDialog(null,"Usuario no existe o fue desactivado");
//                usersVO.setPassword("");
//                usersVO.setUsername("");
//            }
//        }catch (Exception e){
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);;
//            System.out.println("Login: " + e);
//        }
//
//        return auth;
//    }
//

}
