package com.ticketsCode.ticket.Util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PdfPrint {

    public void printTicket() throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A5);
            document.addPage(page);
            java.util.Date date = new java.util.Date();
            DateFormat timeDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setLeading(14.5f);
            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.newLineAtOffset( 0, page.getMediaBox().getHeight() - 35);
            String separator = "===========================";
            String title = " Tiquete de vieaje ";
            contentStream.showText(separator);
            contentStream.newLine();
            contentStream.showText(title);
            contentStream.newLine();
            contentStream.showText(separator);
            contentStream.newLine();
            contentStream.showText(timeDate.format(date).toString());
            contentStream.newLine();


            contentStream.endText();

            // Image
            PDImageXObject image = PDImageXObject.createFromFile("Qr.png",document);
            contentStream.drawImage(image, 0, page.getMediaBox().getHeight() - 150, image.getWidth() / 5, image.getHeight() / 5);
//            contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);

            contentStream.close();

            document.save("document.pdf");
        }

    }



}
