package com.ticketsCode.ticket.Util;


import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;


import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintEpson {

    public PrintEpson() {

    }

    public void printTickets(String qrDAO) {
        int filas = 21;
        int linea = 0;

        PrinterMatrix printer = new PrinterMatrix();
        Extenso extensive = new Extenso();

        extensive.setNumber(10.30);
        printer.setOutSize(filas, 25);

        java.util.Date date = new java.util.Date();
        DateFormat timeDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        //imprimir = 1° parte 1a 32

        printer.printTextWrap(linea, 1, 0, 31, "===============================");
        linea++;
        printer.printTextWrap(linea, 1, 10, 32, "Tiquete de viaje");
        linea++;
        printer.printTextWrap(linea, 1, 0, 32, "====================================");
        linea++;
        printer.printTextWrap(linea, 1, 0, 12, "Hora & Fecha");
        printer.printTextWrap(linea, 1, 15, 32, timeDate.format(date).toString());
        linea++;
        printer.printTextWrap(linea, 1, 0, 60, "QR: " + qrDAO);
        linea++;
        printer.printTextWrap(linea, 1, 0, 32, "-------------------------------------");
        linea++;
        printer.printTextWrap(linea, 1, 0, 32, " ");
        linea++;
        printer.printTextWrap(linea, 1, 0, 32, "¡Gracias por su preferencia!");
        linea++;

        //Crear carpeta temp en C

        printer.toFile("impresion.txt");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("imprimir: " + e.getMessage());
            System.out.println(e);
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaPrintService != null) {
            DocPrintJob printJob = defaPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintEpson.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}


