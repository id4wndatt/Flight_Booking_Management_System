package com.dnd.fbs.services.PdfService;

import com.dnd.fbs.payload.CostStatistics;
import com.dnd.fbs.payload.CostStatisticsByQuarter;
import com.dnd.fbs.payload.TicketStatistics;
import com.dnd.fbs.payload.TicketStatisticsByQuarter;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.util.List;


@Service
public class GeneratePdf {
    public void htmlToPdf(String processedHtml,String order) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
        DefaultFontProvider defaultFont = new DefaultFontProvider(false,true,false);
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setFontProvider(defaultFont);
        HtmlConverter.convertToPdf(processedHtml,pdfWriter,converterProperties);
        String path = FileSystems.getDefault().getPath(new String("./")).toAbsolutePath().getParent() + "\\src\\main\\resources\\static\\PdfOrders\\" + order + ".pdf";
        FileOutputStream fout = new FileOutputStream(path);
        byteArrayOutputStream.writeTo(fout);
        byteArrayOutputStream.close();
        byteArrayOutputStream.flush();
        fout.close();
    }
    
//    public void createTicketByMonth(List<TicketStatistics> ticketDataList, int year, int numberOfYear, HttpServletResponse response) throws IOException {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=ticket_report.pdf");
//
//        // Create PDF writer
//        PdfWriter writer = new PdfWriter(response.getOutputStream());
//        
//        // Create PDF document
//        PdfDocument pdfDocument = new PdfDocument(writer);
//        Document document = new Document(pdfDocument);
//
//        // Thêm tiêu đề
//        document.add(new Paragraph("Báo cáo vé bán theo tháng").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));
//
//        // Tạo bảng với 3 cột
//        Table table = new Table(3); // 3 cột: Month, Year, Number Ticket
//        table.addHeaderCell(new Cell().add(new Paragraph("Month")).setTextAlignment(TextAlignment.CENTER));
//        table.addHeaderCell(new Cell().add(new Paragraph("Year")).setTextAlignment(TextAlignment.CENTER));
//        table.addHeaderCell(new Cell().add(new Paragraph("Number Ticket")).setTextAlignment(TextAlignment.CENTER));
//
//        // Thêm dữ liệu vào bảng
//        for (TicketStatistics ticketStatistics : ticketDataList) {
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(ticketStatistics.getMonth()))).setTextAlignment(TextAlignment.CENTER));
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(ticketStatistics.getYear()))).setTextAlignment(TextAlignment.CENTER));
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(ticketStatistics.getTicketCount()))).setTextAlignment(TextAlignment.CENTER));
//        }
//        
//        // Căn giữa bảng
//        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        
//        // Thêm bảng vào tài liệu
//        document.add(table);
//        
//        document.close();
//    }
//    
//    public void createTicketByQuarterReport(List<TicketStatisticsByQuarter> ticketDataList, HttpServletResponse response) throws IOException {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=ticket_by_quarter_report.pdf");
//
//        PdfWriter writer = new PdfWriter(response.getOutputStream());
//        PdfDocument pdfDocument = new PdfDocument(writer);
//        Document document = new Document(pdfDocument);
//
//        document.add(new Paragraph("Báo cáo vé bán theo quý").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));
//
//        Table table = new Table(3);
//        table.addHeaderCell(new Cell().add(new Paragraph("Quarter")).setTextAlignment(TextAlignment.CENTER));
//        table.addHeaderCell(new Cell().add(new Paragraph("Year")).setTextAlignment(TextAlignment.CENTER));
//        table.addHeaderCell(new Cell().add(new Paragraph("Number Ticket")).setTextAlignment(TextAlignment.CENTER));
//
//        for (TicketStatisticsByQuarter ticketStatistics : ticketDataList) {
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(ticketStatistics.getQuarter()))).setTextAlignment(TextAlignment.CENTER));
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(ticketStatistics.getYear()))).setTextAlignment(TextAlignment.CENTER));
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(ticketStatistics.getTicketCount()))).setTextAlignment(TextAlignment.CENTER));
//        }
//
//        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        document.add(table);
//        document.close();
//    }
//    
//    public void createCostByMonthReport(List<CostStatistics> costDataList, int year, int numberOfYear, HttpServletResponse response) throws IOException {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=cost_by_month_report.pdf");
//
//        PdfWriter writer = new PdfWriter(response.getOutputStream());
//        PdfDocument pdfDocument = new PdfDocument(writer);
//        Document document = new Document(pdfDocument);
//
//        document.add(new Paragraph("Báo cáo doanh thu theo tháng").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));
//
//        Table table = new Table(3);
//        table.addHeaderCell(new Cell().add(new Paragraph("Month")).setTextAlignment(TextAlignment.CENTER));
//        table.addHeaderCell(new Cell().add(new Paragraph("Year")).setTextAlignment(TextAlignment.CENTER));
//        table.addHeaderCell(new Cell().add(new Paragraph("Total Cost")).setTextAlignment(TextAlignment.CENTER));
//
//        for (CostStatistics costStatistics : costDataList) {
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(costStatistics.getMonth()))).setTextAlignment(TextAlignment.CENTER));
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(costStatistics.getYear()))).setTextAlignment(TextAlignment.CENTER));
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(costStatistics.getTicketCost()))).setTextAlignment(TextAlignment.CENTER));
//        }
//
//        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        document.add(table);
//        document.close();
//    }
//    
//    public void createCostByQuarterReport(List<CostStatisticsByQuarter> costStatisticsByQuarter, HttpServletResponse response) throws IOException {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=cost_by_quarter_report.pdf");
//
//        PdfWriter writer = new PdfWriter(response.getOutputStream());
//        PdfDocument pdfDocument = new PdfDocument(writer);
//        Document document = new Document(pdfDocument);
//
//        document.add(new Paragraph("Báo cáo doanh thu theo quý").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));
//
//        Table table = new Table(3);
//        table.addHeaderCell(new Cell().add(new Paragraph("Quarter")).setTextAlignment(TextAlignment.CENTER));
//        table.addHeaderCell(new Cell().add(new Paragraph("Year")).setTextAlignment(TextAlignment.CENTER));
//        table.addHeaderCell(new Cell().add(new Paragraph("Total Cost")).setTextAlignment(TextAlignment.CENTER));
//
//        for (CostStatisticsByQuarter costStatistics : costStatisticsByQuarter) {
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(costStatistics.getQuarter()))).setTextAlignment(TextAlignment.CENTER));
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(costStatistics.getYear()))).setTextAlignment(TextAlignment.CENTER));
//            table.addCell(new Cell().add(new Paragraph(String.valueOf(costStatistics.getTicketCost()))).setTextAlignment(TextAlignment.CENTER));
//        }
//
//        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        document.add(table);
//        document.close();
//    }

}
