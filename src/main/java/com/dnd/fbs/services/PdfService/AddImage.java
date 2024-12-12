package com.dnd.fbs.services.PdfService;




import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

@Service
public class AddImage {
    public void insertQrOrder(String qrcode) throws IOException, DocumentException {
        String pdfpath = FileSystems.getDefault().getPath(new String("./")).toAbsolutePath().getParent() + "\\src\\main\\resources\\static\\PdfOrders\\" + qrcode + ".pdf";

        OutputStream fos = new FileOutputStream(pdfpath);

        PdfReader pdfReader = new PdfReader(pdfpath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);

        //Image to be added in existing pdf file.
        String imgPath = FileSystems.getDefault().getPath(new String("./")).toAbsolutePath().getParent() + "\\src\\main\\resources\\static\\qrcodes\\" + qrcode + ".jpg";

        Image image = Image.getInstance(imgPath);
        image.scaleAbsolute(150, 150); //Scale image's width and height
        image.setAbsolutePosition(500, 500); //Set position for image in PDF

         //loop on all the PDF pages
         //i is the pdfPageNumber
        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
            //getOverContent() allows you to write pdfContentByte on TOP of existing pdf pdfContentByte.
            //getUnderContent() allows you to write pdfContentByte on BELOW of existing pdf pdfContentByte.
            PdfContentByte pdfContentByte = pdfStamper.getOverContent(i);
            pdfContentByte.addImage(image);
            System.out.println("Image added in "+pdfpath);
        }
        pdfStamper.close();

        System.out.println("Modified PDF created in >> "+ pdfpath);
        System.out.println("Insert image successfully!");
    }
}
