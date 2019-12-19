package com.ticketsCode.ticket.Models.Dao;

import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.UsersVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private UsersVO usersVO;

    public UserDAO(UsersVO userVO){
        this.usersVO = userVO;
    }

    public boolean authUser(UsersVO usersVO){
        PreparedStatement st = null;
        DataBaseConnection conn = new DataBaseConnection();
        ResultSet rs;
        Connection connect = conn.getConn();
        String SQL = "SELECT * FROM users WHERE TRUE AND username = ? AND password = ? AND status = 't'";
        try{
            st = connect.prepareStatement(SQL);
            st.setString(1,usersVO.getUsername());
            st.setString(1,usersVO.getPassword().toString());
            rs = st.executeQuery();
        }catch (Exception e){

        }

    }


}
