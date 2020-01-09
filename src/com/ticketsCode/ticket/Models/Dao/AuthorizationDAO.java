package com.ticketsCode.ticket.Models.Dao;

import com.sun.istack.internal.logging.Logger;
import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.Login;
import com.ticketsCode.ticket.Models.Vo.UsersVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class AuthorizationDAO {

    public AuthorizationDAO() {
    }

    java.util.Date date = new java.util.Date();
    DateFormat timeDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public boolean authUser(Login login) {
        PreparedStatement st;
        DataBaseConnection conn = new DataBaseConnection();
        Connection connect = conn.getConn();
        ResultSet rs;
        String SQL = "SELECT  \n" +
                "        u.username\n" +
                "        ,initcap(u.names)\n" +
                "        ,u.type_category\n" +
                "        ,u.password\n" +
                "        ,u.status\n" +
                "        ,c.name\n" +
                "    FROM users u\n" +
                "        LEFT JOIN user_control u_c\n" +
                "            ON u_c.user_id = u.user_id\n" +
                "        LEFT JOIN controls c\n" +
                "        ON c.control_id = u_c.control_id\n" +
                "    WHERE TRUE \n" +
                "        AND u.username = ?\n" +
                "        AND u.password = ?\n" +
                "        AND u.status = 't'\n" +
                "        LIMIT 1";
        try {
            st = connect.prepareStatement(SQL);
            st.setString(1, login.getUsername());
            st.setString(2, login.getPassword());
            rs = st.executeQuery();
            if (rs.next()) {
                if (login.getPassword().equals(rs.getString(4))) {
                    login.setUsername(rs.getString(1));
                    login.setStatus(rs.getBoolean(5));
                    login.setTypeCategoty(rs.getString(3));
                    login.setNames(rs.getString(2));
//                    System.out.println("user: " + st);
                    LOGGER.log(Level.INFO, "Buscando usuario");
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("User: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("User Exception: " + e.getMessage());
        }
        return false;
    }


}
