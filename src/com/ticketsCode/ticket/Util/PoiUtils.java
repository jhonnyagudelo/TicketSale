package com.ticketsCode.ticket.Util;


import com.ticketsCode.ticket.Models.Dao.SearchDAO;
import com.ticketsCode.ticket.Models.Db.DataBaseConnection;
import com.ticketsCode.ticket.Models.Vo.DataExport;
import com.ticketsCode.ticket.Views.TravelHistory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jdk.nashorn.internal.objects.NativeMath.log;

public class PoiUtils {
    private SearchDAO searchDAO;
    private DataExport dataExport;
    private TravelHistory travelHistory;
    private static final Logger LOGGER = Logger.getLogger("mx.com.hash.newexcel.ExcelOOXML");

    public PoiUtils(SearchDAO searchDAO, DataExport dataExport, TravelHistory travelHistory) {
        this.searchDAO = searchDAO;
        this.dataExport = dataExport;
        this.travelHistory = travelHistory;

    }


    public PoiUtils() {

    }


    public void createExcel() {
        //Crear un Excel
        java.util.Date date = new java.util.Date();
        DateFormat timeDate = new SimpleDateFormat("dd-MM-yyyy");
        File archivo = new File("Reporte.xlsx");
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Reporte " + timeDate.format(date).toString());
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

            String[] t = {"#interno", "Destino", "Total tiquetes", "Fecha compra"};

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
            fontheader.setFontHeightInPoints((short)12);
            headerStyle.setFont(fontheader);

            Row rowHeader = sheet.createRow(8);
            for (int i = 0; i < t.length ; i++) {
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




            FileOutputStream fileOut = new FileOutputStream(archivo);
            book.write(fileOut);
            fileOut.close();
            LOGGER.log(Level.INFO, "Archivo creado existosamente en {0}", archivo.getAbsolutePath());
        } catch (FileNotFoundException e) {
            System.out.println("Error Excel: " + e.getMessage());
            Logger.getLogger(PoiUtils.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            System.out.println("Error Excel: " + e.getMessage());
            Logger.getLogger(PoiUtils.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}