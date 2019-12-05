package com.ticketsCode.ticket.Models.Db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    
    private final String USER = "postgres";
    private final String LOCALHOST = "localhost:5432";
    private final String PASSWORD = "1113645020";
    private  String SERVER = "";
    private final String URL = "jdbc:postgresql://localhost:5432/tiquetes";
    private static Connection conn = null;

    public DataBaseConnection(String tiquetes){
        this.SERVER = "jdbc:postgresql://"+this.LOCALHOST+"/"+tiquetes;
        //Registrar el driver
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(this.SERVER,this.USER,this.PASSWORD);

        } catch(ClassNotFoundException e){
            System.out.println("Error al registrar driver");
            System.exit(0);
            JOptionPane.showMessageDialog(null,"Error al registrar driver"+ e.getMessage());
        } catch (SQLException e){
            System.out.println("Error al conectar con el servidor");
            System.exit(0);
            JOptionPane.showMessageDialog(null,"Error al conectar con el servidor"+ e.getMessage());
        }
        System.out.println("Conectado a " +tiquetes);
    }

    public DataBaseConnection() {

    }

    public Connection getConn(){
        System.out.println("Connected to the PosgreSQL");
        return conn;
    }
    public void disconnect(){
        System.out.println("Disconnect to the PosgreSQL");
        conn = null;
    }

}
