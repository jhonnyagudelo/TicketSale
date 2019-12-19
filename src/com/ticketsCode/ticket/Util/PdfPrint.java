package com.ticketsCode.ticket.Util;

import com.ticketsCode.ticket.Models.Vo.DestinationsVO;
import com.ticketsCode.ticket.Models.Vo.TicketVO;
import com.ticketsCode.ticket.Views.TicketSales;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.printing.PDFPageable;

import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class PdfPrint {
    private DestinationsVO destinationsVO;

    public PdfPrint() {

    }

    public void printTicket() throws IOException, PrinterException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A6);
            document.addPage(page);
            java.util.Date date = new java.util.Date();
            DateFormat timeDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setLeading(14.5f);
            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.newLineAtOffset(10, page.getMediaBox().getHeight() + -5);

            String separator = "=================================";
            String title = "                        Tiquete de viaje ";
            String footer = "Gracias por tu preferencia";

            //agregar a la tirilla
            contentStream.showText(separator);
            contentStream.newLine();
            contentStream.showText(title);
            contentStream.newLine();
            contentStream.showText(separator);
            contentStream.newLine();
//            contentStream.showText("Destino: " + destinationsVO.getName().toString());
//            contentStream.newLine();
            //            contentStream.showText(":Empresa: " + ticketVO.getCompany().toString());
            //            contentStream.newLine();
            contentStream.showText(timeDate.format(date).toString());
            contentStream.newLine();
            contentStream.showText(footer);
            contentStream.newLine();

            contentStream.endText();

            // Image
            PDImageXObject image = PDImageXObject.createFromFile("Qr.png", document);
            contentStream.drawImage(image, 50, page.getMediaBox().getHeight() - 200, image.getWidth() / 5, image.getHeight() / 5);

            contentStream.close();
            document.save("document.pdf");
        }
        PDDocument document = PDDocument.load(new File("document.pdf"));
        PrinterJob job = PrinterJob.getPrinterJob();
        LOGGER.log(Level.INFO, "Mostrando el dialogo de impresion");
        if (job.printDialog() == true) {
            job.setPageable(new PDFPageable(document));

            LOGGER.log(Level.INFO, "Imprimiendo documento");
            job.print();
        }

    }


}
