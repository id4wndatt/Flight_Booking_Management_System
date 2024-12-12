package com.dnd.fbs.security.configuration;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class Barcode {
    public void create_bar_code(String barcode){
        try{
            String path = FileSystems.getDefault().getPath(new String("./")).toAbsolutePath().getParent() + "\\src\\main\\resources\\static\\barcodes\\" + barcode + ".jpg";
            Code128Writer writer = new Code128Writer();

            BitMatrix matrix = writer.encode(barcode, BarcodeFormat.CODE_128,500,200);
            MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(path));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void create_qr_code(String qrcode){
        try{
            String path = FileSystems.getDefault().getPath(new String("./")).toAbsolutePath().getParent() + "\\src\\main\\resources\\static\\qrcodes\\" + qrcode + ".jpg";
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode(qrcode,BarcodeFormat.QR_CODE,400,400);
            MatrixToImageWriter.writeToPath(matrix,"jpg",Paths.get(path));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
