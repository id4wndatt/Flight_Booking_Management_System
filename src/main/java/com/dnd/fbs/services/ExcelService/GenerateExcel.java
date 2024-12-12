package com.dnd.fbs.services.ExcelService;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import com.dnd.fbs.payload.CostStatistics;
import com.dnd.fbs.payload.CostStatisticsByQuarter;
import com.dnd.fbs.payload.TicketStatistics;
import com.dnd.fbs.payload.TicketStatisticsByQuarter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class GenerateExcel {
	public void createTicketByMonthReport(List<TicketStatistics> ticketDataList, HttpServletResponse response) throws IOException {
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=ticket_by_month_report.xlsx");

	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Báo cáo vé bán theo tháng");

	    //Thêm tên báo cáo
	    Row titleRow = sheet.createRow(0);
	    Cell titleCell = titleRow.createCell(0);
	    titleCell.setCellValue("BÁO CÁO VÉ BÁN THEO THÁNG");

	    //Tạo kiểu font cho tiêu đề
	    CellStyle titleStyle = workbook.createCellStyle();
	    Font titleFont = workbook.createFont();
	    titleFont.setBold(true); //In đậm
	    titleFont.setFontHeightInPoints((short) 14); //Kích thước font
	    titleStyle.setFont(titleFont); //Gán kiểu font cho CellStyle
	    titleStyle.setAlignment(HorizontalAlignment.CENTER); //Căn giữa
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2)); //Merge cột từ 0 đến 2
	    titleCell.setCellStyle(titleStyle);

	    //Tạo hàng tiêu đề
	    Row headerRow = sheet.createRow(1);
	    String[] headers = {"Month", "Year", "Number Ticket"};
	    CellStyle headerStyle = workbook.createCellStyle();
	    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
	    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerStyle.setBorderBottom(BorderStyle.THIN);
	    headerStyle.setBorderTop(BorderStyle.THIN);
	    headerStyle.setBorderLeft(BorderStyle.THIN);
	    headerStyle.setBorderRight(BorderStyle.THIN);

	    for (int i = 0; i < headers.length; i++) {
	        Cell cell = headerRow.createCell(i);
	        cell.setCellValue(headers[i]);
	        cell.setCellStyle(headerStyle);
	    }

	    //Thêm dữ liệu vào bảng
	    int rowNum = 2;
	    for (TicketStatistics ticketStatistics : ticketDataList) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(ticketStatistics.getMonth());
	        row.createCell(1).setCellValue(ticketStatistics.getYear());
	        row.createCell(2).setCellValue(ticketStatistics.getTicketCount());
	    }

	    //Tự động căn chỉnh kích thước cột
	    for (int i = 0; i < headers.length; i++) {
	        sheet.autoSizeColumn(i);
	    }

	    //Ghi file Excel ra response
	    workbook.write(response.getOutputStream());
	    workbook.close();
	}
	
	public void createTicketByQuarterReport(List<TicketStatisticsByQuarter> ticketDataList, HttpServletResponse response) throws IOException {
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=ticket_by_quarter_report.xlsx");

	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Báo cáo vé bán theo quý");

	    Row titleRow = sheet.createRow(0);
	    Cell titleCell = titleRow.createCell(0);
	    titleCell.setCellValue("BÁO CÁO VÉ BÁN THEO QUÝ");

	    CellStyle titleStyle = workbook.createCellStyle();
	    Font titleFont = workbook.createFont();
	    titleFont.setBold(true);
	    titleFont.setFontHeightInPoints((short) 14);
	    titleStyle.setFont(titleFont);
	    titleStyle.setAlignment(HorizontalAlignment.CENTER);
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
	    titleCell.setCellStyle(titleStyle);

	    Row headerRow = sheet.createRow(1);
	    String[] headers = {"Quarter", "Year", "Number Ticket"};
	    CellStyle headerStyle = workbook.createCellStyle();
	    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
	    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerStyle.setBorderBottom(BorderStyle.THIN);
	    headerStyle.setBorderTop(BorderStyle.THIN);
	    headerStyle.setBorderLeft(BorderStyle.THIN);
	    headerStyle.setBorderRight(BorderStyle.THIN);

	    for (int i = 0; i < headers.length; i++) {
	        Cell cell = headerRow.createCell(i);
	        cell.setCellValue(headers[i]);
	        cell.setCellStyle(headerStyle);
	    }

	    int rowNum = 2;
	    for (TicketStatisticsByQuarter ticketStatistics : ticketDataList) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(ticketStatistics.getQuarter());
	        row.createCell(1).setCellValue(ticketStatistics.getYear());
	        row.createCell(2).setCellValue(ticketStatistics.getTicketCount());
	    }

	    for (int i = 0; i < headers.length; i++) {
	        sheet.autoSizeColumn(i);
	    }

	    workbook.write(response.getOutputStream());
	    workbook.close();
	}

	public void createCostByMonthReport(List<CostStatistics> costDataList, HttpServletResponse response) throws IOException {
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=cost_by_month_report.xlsx");

	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Báo cáo doanh thu theo tháng");

	    Row titleRow = sheet.createRow(0);
	    Cell titleCell = titleRow.createCell(0);
	    titleCell.setCellValue("BÁO CÁO DOANH THU THEO THÁNG");

	    CellStyle titleStyle = workbook.createCellStyle();
	    Font titleFont = workbook.createFont();
	    titleFont.setBold(true);
	    titleFont.setFontHeightInPoints((short) 14);
	    titleStyle.setFont(titleFont);
	    titleStyle.setAlignment(HorizontalAlignment.CENTER);
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
	    titleCell.setCellStyle(titleStyle);

	    Row headerRow = sheet.createRow(1);
	    String[] headers = {"Month", "Year", "Total Cost"};
	    CellStyle headerStyle = workbook.createCellStyle();
	    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
	    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerStyle.setBorderBottom(BorderStyle.THIN);
	    headerStyle.setBorderTop(BorderStyle.THIN);
	    headerStyle.setBorderLeft(BorderStyle.THIN);
	    headerStyle.setBorderRight(BorderStyle.THIN);

	    for (int i = 0; i < headers.length; i++) {
	        Cell cell = headerRow.createCell(i);
	        cell.setCellValue(headers[i]);
	        cell.setCellStyle(headerStyle);
	    }

	    int rowNum = 2;
	    for (CostStatistics costStatistics : costDataList) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(costStatistics.getMonth());
	        row.createCell(1).setCellValue(costStatistics.getYear());
	        row.createCell(2).setCellValue(costStatistics.getTicketCost());
	    }

	    for (int i = 0; i < headers.length; i++) {
	        sheet.autoSizeColumn(i);
	    }

	    workbook.write(response.getOutputStream());
	    workbook.close();
	}

	public void createCostByQuarterReport(List<CostStatisticsByQuarter> costStatisticsByQuarter, HttpServletResponse response) throws IOException {
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=cost_by_quarter_report.xlsx");

	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Báo cáo doanh thu theo quý");

	    Row titleRow = sheet.createRow(0);
	    Cell titleCell = titleRow.createCell(0);
	    titleCell.setCellValue("BÁO CÁO DOANH THU THEO QUÝ");

	    CellStyle titleStyle = workbook.createCellStyle();
	    Font titleFont = workbook.createFont();
	    titleFont.setBold(true);
	    titleFont.setFontHeightInPoints((short) 14);
	    titleStyle.setFont(titleFont);
	    titleStyle.setAlignment(HorizontalAlignment.CENTER);
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
	    titleCell.setCellStyle(titleStyle);

	    Row headerRow = sheet.createRow(1);
	    String[] headers = {"Quarter", "Year", "Total Cost"};
	    CellStyle headerStyle = workbook.createCellStyle();
	    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
	    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerStyle.setBorderBottom(BorderStyle.THIN);
	    headerStyle.setBorderTop(BorderStyle.THIN);
	    headerStyle.setBorderLeft(BorderStyle.THIN);
	    headerStyle.setBorderRight(BorderStyle.THIN);

	    for (int i = 0; i < headers.length; i++) {
	        Cell cell = headerRow.createCell(i);
	        cell.setCellValue(headers[i]);
	        cell.setCellStyle(headerStyle);
	    }

	    int rowNum = 2;
	    for (CostStatisticsByQuarter costStatistics : costStatisticsByQuarter) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(costStatistics.getQuarter());
	        row.createCell(1).setCellValue(costStatistics.getYear());
	        row.createCell(2).setCellValue(costStatistics.getTicketCost());
	    }

	    for (int i = 0; i < headers.length; i++) {
	        sheet.autoSizeColumn(i);
	    }

	    workbook.write(response.getOutputStream());
	    workbook.close();
	}
}
