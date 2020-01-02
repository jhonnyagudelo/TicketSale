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
    private JLabel lblInternal_number, lblTotal, lblDestiny, lblDestinyExport;
    public JTextField tfDestinyExport;
    public JButton btnInternal_number, btnTotal, btnDestiny, btnSave, btnCancel, btnRutaExport;
    private JMenuBar menuBar;
    public JFileChooser file;
    public FileNameExtensionFilter filter;
    public JMenu ulFile, ulTickets, ulVehicles, ulHelp;
    public JMenuItem liClose, liList, liRegistration, liSale, liAbout, liList1, liDelete, liExport;
    private FileWriter fichero = null;
    private PrintWriter pw = null;

    public ExportExcel() {
        setTitle("Exportacion de datos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(450, 100, 500, 380);

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
        liList = new JMenuItem("Consulta");
        ulTickets.add(liList);
        //item menu vehiculos
        liRegistration = new JMenuItem("Registro vehicular");
        ulVehicles.add(liRegistration);

        liDelete = new JMenuItem("Eliminar");
        ulVehicles.add(liDelete);
        ulVehicles.add(liDelete);

        liList1 = new JMenuItem("Consulta");
        ulVehicles.add(liList1);
        //acerca de
        liAbout = new JMenuItem("Acerca de.");
        ulHelp.add(liAbout);

        dataPane = new JDesktopPane();
        dataPane.setBounds(20, 35, 250, 160);
        dataPane.setBackground(SystemColor.control);
        dataPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        dataPane.setBorder(BorderFactory.createTitledBorder("Datos a exportar"));
        contentPane.add(dataPane);
        dataPane.setLayout(null);

        lblDestiny = new JLabel("Destino:");
        lblDestiny.setBounds(10, 40, 100, 14);
        dataPane.add(lblDestiny);

        lblDestinyExport = new JLabel("Ruta de Exportacion: ");
        lblDestinyExport.setBounds(40, 220, 150, 20);
        contentPane.add(lblDestinyExport);


        lblInternal_number = new JLabel("Numero interno:");
        lblInternal_number.setBounds(10, 80, 100, 14);
        dataPane.add(lblInternal_number);

        lblTotal = new JLabel("Total:");
        lblTotal.setBounds(10, 120, 100, 14);
        dataPane.add(lblTotal);

        tfDestinyExport = new JTextField();
        tfDestinyExport.setBounds(160, 219, 242, 25);
        contentPane.add(tfDestinyExport);

        btnDestiny = new JButton("Seleccionar");
        btnDestiny.setBounds(120, 35, 100, 20);
        dataPane.add(btnDestiny);

        btnInternal_number = new JButton("Seleccionar");
        btnInternal_number.setBounds(120, 75, 100, 20);
        dataPane.add(btnInternal_number);

        btnTotal = new JButton("Seleccionar");
        btnTotal.setBounds(120, 115, 100, 20);
        dataPane.add(btnTotal);

        btnSave = new JButton("Guardar");
        btnSave.setBounds(325, 80, 100, 25);
        contentPane.add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(325, 120, 100, 25);
        contentPane.add(btnCancel);

        btnRutaExport = new JButton("...");
        btnRutaExport.setBounds(400, 219, 24, 24);
        contentPane.add(btnRutaExport);

    }

    public boolean saveExcel() {
        file = new JFileChooser();
        file.setMultiSelectionEnabled(true);
        filter = new FileNameExtensionFilter("xlsx");
        file.setFileFilter(filter);
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        file.setCurrentDirectory(new File("C:"));
        int retVal = file.showSaveDialog(null);

        if (retVal == file.APPROVE_OPTION) {
            try {
                fichero = new FileWriter(file.getSelectedFile(), false);
                LOGGER.log(Level.INFO, "Creando Excel");
            } catch (Exception e1) {
                LOGGER.log(Level.INFO,"Error" + e1.getMessage());
                System.out.println("errorExcel: " + e1.getMessage());
            }
        }
        return false;
    }


    public void guardarFichero(ExcelUtil excelUtil){
        try {
            fichero = new FileWriter(String.valueOf(excelUtil), true);
            fichero.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error al guardar el documento", "Error",JOptionPane.ERROR_MESSAGE);
            System.out.println("ErrorExcelS: " + e.getMessage());;
            e.printStackTrace();
        }
    }


}