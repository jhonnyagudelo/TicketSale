package com.ticketsCode.ticket.Views;

import com.ticketsCode.ticket.Util.ExcelUtil;
import org.apache.pdfbox.jbig2.util.log.Logger;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ExportExcel extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExportExcel frame = new ExportExcel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    System.out.println("Jframe: " + e.getMessage());
                }
            }
        });
    }

    private JPanel contentPane;
    public JDesktopPane dataPane;
    private JLabel lblInternal_number, lblTotal, lblDestiny;
    public JButton btnInternal_number, btnTotal, btnDestiny, btnSave, btnCancel, btnRutaExport;
    private JMenuBar menuBar;
    public JMenu ulFile, ulTickets, ulVehicles, ulHelp;
    public JMenuItem liClose, liList, liRegistration, liSale, liAbout, liList1, liDelete;

    public ExportExcel() {
        setTitle("Exportacion de datos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(450, 100, 500, 300);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Barra Menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        ulFile = new JMenu("Archivo");
        menuBar.add(ulFile);
        ulTickets = new JMenu("Tiquetes");
        menuBar.add(ulTickets);
        ulVehicles = new JMenu("Vehiculos");
        menuBar.add(ulVehicles);
        ulHelp = new JMenu("Ayuda");
        menuBar.add(ulHelp);

        //item Menu
        //item Menu Archivo
        liClose = new JMenuItem("Cerrar");
        ulFile.add(liClose);
        //item menu venta
        liSale = new JMenuItem("Venta tiquetes");
        ulTickets.add(liSale);


        //item menu vehiculos
        liRegistration = new JMenuItem("Registro vehicular");
        ulVehicles.add(liRegistration);

        liList1 = new JMenuItem("Consulta");
        ulVehicles.add(liList1);
        //acerca de
        liAbout = new JMenuItem("Acerca de.");
        ulHelp.add(liAbout);

        dataPane = new JDesktopPane();
        dataPane.setBounds(120, 35, 250, 160);
        dataPane.setBackground(SystemColor.control);
        dataPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        dataPane.setBorder(BorderFactory.createTitledBorder("Datos a exportar"));
        contentPane.add(dataPane);
        dataPane.setLayout(null);

        lblDestiny = new JLabel("Porcentaje:");
        lblDestiny.setBounds(10, 40, 100, 14);
        dataPane.add(lblDestiny);


        lblInternal_number = new JLabel("Numero interno:");
        lblInternal_number.setBounds(10, 80, 100, 14);
        dataPane.add(lblInternal_number);

        lblTotal = new JLabel("Total:");
        lblTotal.setBounds(10, 120, 100, 14);
        dataPane.add(lblTotal);


        btnDestiny = new JButton("Seleccionar");
        btnDestiny.setBounds(120, 35, 100, 20);
        dataPane.add(btnDestiny);

        btnInternal_number = new JButton("Seleccionar");
        btnInternal_number.setBounds(120, 75, 100, 20);
        dataPane.add(btnInternal_number);

        btnTotal = new JButton("Seleccionar");
        btnTotal.setBounds(120, 115, 100, 20);
        dataPane.add(btnTotal);


    }


//
//
//    public boolean saveExcel() {
//        file = new JFileChooser();
//        file.setMultiSelectionEnabled(true);
//        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int retVal = file.showSaveDialog(this);
//        if (retVal == file.APPROVE_OPTION) {
//            try {
//                file.setSelectedFile(new File("Reporte.xlsx"));
//                LOGGER.log(Level.INFO, "Creando Excel");
//            } catch (Exception e1) {
//                LOGGER.log(Level.INFO,"Error" + e1.getMessage());
//                System.out.println("errorExcel: " + e1.getMessage());
//            }
//        }
//        return false;
//    }
//
//
//    public void guardarFichero(File file){
//        try {
//
//
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null,"Error al guardar el documento", "Error",JOptionPane.ERROR_MESSAGE);
//            System.out.println("ErrorExcelS: " + e.getMessage());;
//            e.printStackTrace();
//        }
//    }


}