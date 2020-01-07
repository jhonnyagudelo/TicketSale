package com.ticketsCode.ticket.Util;

import com.ticketsCode.ticket.Models.Dao.SearchDAO;
import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.DataExport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelPercentage extends Component {
//    private SearchDAO searchDAO;
//    private DataExport dataExport;


    private static final Logger LOGGER = Logger.getLogger("mx.com.hash.newexcel.ExcelOOXML");

//    public ExcelPercentage(SearchDAO searchDAO, DataExport dataExport) {
//        this.searchDAO = searchDAO;
//        this.dataExport = dataExport;
//
//    }

    public ExcelPercentage(){}

    public File excelPorcentaje(DataExport data) {
        //Crear un Excel
        Date date = new Date();
        DateFormat timeDate = new SimpleDateFormat("dd-MM-yyyy");
        File archivo = new File("PorcentajeDestino.xlsx");
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Reporte" + timeDate.format(date));
        try {

            //traer imagen y convertirla
            InputStream is = new FileInputStream("src\\com\\ticketsCode\\ticket\\Img\\logoS.jpeg");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();

            //agregar imagen al archivo
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            //Ancho de la iamgen

            ClientAnchor width = help.createClientAnchor();
            //posicionar la imagen en columna y fila
            width.setCol1(0);
            width.setRow1(0);

            //creat una variable de tipo picture para crear la iamgen
            Picture pict = draw.createPicture(width, imgIndex);
            //donde empieza y espacio
            pict.resize(4, 6);

            //estilos
            CellStyle style = book.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font fountTile = book.createFont();
            fountTile.setFontName("Calibri Light");
            fountTile.setBold(true);
            fountTile.setFontHeightInPoints((short) 14);
            style.setFont(fountTile);

            //Crear fila donde esta el titulo
            Row title = sheet.createRow(6);
            Cell celTitle = title.createCell(0);
            celTitle.setCellStyle(style);
            //Creamos un estilo para el encabezado
            celTitle.setCellValue("Reporte por vehiculo");
            sheet.addMergedRegion(new CellRangeAddress(6, 7, 0, 3));

            String[] t = {"Destino", "Total tiquetes", "Porcentaje", "Fecha compra"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font fontheader = book.createFont();
            fontheader.setFontName("Arial");
            fontheader.setBold(true);
            fontheader.setColor(IndexedColors.WHITE.getIndex());
            fontheader.setFontHeightInPoints((short) 12);
            headerStyle.setFont(fontheader);

            Row rowHeader = sheet.createRow(8);
            for (int i = 0; i < t.length; i++) {
                Cell celHeader = rowHeader.createCell(i);
                celHeader.setCellStyle(headerStyle);
                celHeader.setCellValue(t[i]);
            }

            //ingresar datos de Postgres
            int numRowData = 9;
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);


            DataBaseConnection conn = new DataBaseConnection();
            CallableStatement cs;
            ResultSet rs;
            String SQL = "SELECT * FROM travel_historyp(?,?,?)";
            cs = conn.getConn().prepareCall(SQL);
            cs.setDate(1, (data.getDateStart()));
            cs.setDate(2, (data.getDateEnd()));
            cs.setInt(3, (data.getCompany()));
            rs = cs.executeQuery();
            int numCol = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Row fileDates = sheet.createRow(numRowData);
                for (int a = 0; a < numCol; a++) {
                    Cell cellData = fileDates.createCell(a);
                    cellData.setCellStyle(datosEstilo);

                    if (a == 1 || a == 2) {
                        cellData.setCellValue(rs.getDouble(a + 1));
                        System.out.println("int: " + rs.getInt(a + 1));
                        System.out.println("row: " + cellData);
                    } else {
                        cellData.setCellValue(rs.getString(a + 1));
                        System.out.println("String: " + rs.getString(a + 1));
                    }
                }
                numRowData++;
            }

            for (int i = 0; i < t.length; i++) {
                sheet.autoSizeColumn(i);
                sheet.setZoom(100);
            }


            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Excel file ", "xlsx"));
//            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option  = chooser.showSaveDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                if(chooser.getSelectedFile()!=null){
                    FileOutputStream fileOut = new FileOutputStream(chooser.getSelectedFile()+ ".xlsx");
                    book.write(fileOut);
                    fileOut.close();
                    File file = new File("PorcentajeDestino.xlsx");
                    JOptionPane.showMessageDialog(null, "El archivo se guardo Exitosamente", "informacion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
//            Desktop.getDesktop().open(file);
            LOGGER.log(Level.INFO, "Archivo creado existosamente en {0}", archivo.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("Error Excel: " + e.getMessage());
            Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            System.out.println("Error Excel: " + e.getMessage());
            Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return archivo;
    }
}








