package com.ticketsCode.ticket.Util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ticketsCode.ticket.Models.Dao.VehicleDAO;

import java.awt.image.BufferedImage;
public class QrGenerate {
    VehicleDAO autoBusDAO;

    public QrGenerate() {

    }

    public BufferedImage createQR(String data, int height, int width) throws WriterException {
        Writer desktop = new QRCodeWriter();
        BitMatrix matrix = desktop.encode(data, BarcodeFormat.QR_CODE, width, height);

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                int grayValue= (matrix.get(j,i)? 0 : 1) & 0xff;
                image.setRGB(j,i,(grayValue == 0 ? 0 : 0xFFFFFF));
            }
        }
        return image;
    }

}
