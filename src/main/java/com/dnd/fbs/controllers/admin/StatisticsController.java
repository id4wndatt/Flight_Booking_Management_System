package com.dnd.fbs.controllers.admin;

import com.dnd.fbs.payload.*;
import com.dnd.fbs.services.OrderInfoService;
import com.dnd.fbs.services.TicketService;
import com.dnd.fbs.services.ExcelService.GenerateExcel;
import com.dnd.fbs.services.PdfService.GeneratePdf;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {
    private TicketService ticketService;
    private OrderInfoService orderInfoService;
    private GenerateExcel generateExcel;

    public StatisticsController(TicketService ticketService, OrderInfoService orderInfoService, GenerateExcel generateExcel) {
        this.ticketService = ticketService;
        this.orderInfoService = orderInfoService;
        this.generateExcel = generateExcel;
    }

    @GetMapping("/ticketByMonth")
    public String getStatisticsTicketByMonth(ModelMap modelMap,
                                             @RequestParam(defaultValue = "2019") int year,
                                             @RequestParam(defaultValue = "5") int numberOfYear,
                                             HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }

        List<TicketStatistics> ticketDataList = ticketService.countTicket();
        List<Integer> uniqueYears = ticketService.getUniqueYear();
        List<Integer> getNumberYearsFrom = ticketService.getNumberYearsFrom(year, numberOfYear);

        modelMap.addAttribute("ticketDataList", ticketDataList);
        modelMap.addAttribute("uniqueYears", uniqueYears);
        modelMap.addAttribute("getNumberYearsFrom", getNumberYearsFrom);
        modelMap.addAttribute("numberOfYear", numberOfYear);
        modelMap.addAttribute("year",year);
        return "admin/statistics/ticketByMonth";
    }
    
    @GetMapping("/printTicketByMonth")
    public void printTicketByMonth(HttpServletResponse response) throws IOException {
        List<TicketStatistics> ticketDataList = ticketService.countTicket(); // Hoặc phương thức phù hợp để lấy dữ liệu vé bán theo tháng
        generateExcel.createTicketByMonthReport(ticketDataList, response);
    }

    @GetMapping("/ticketByQuarter")
    public String getStatisticsTicketByQuarter(ModelMap modelMap,
                                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }
        List<TicketStatisticsByQuarter> ticketStatisticsByQuarters = ticketService.countTicketByQuarter();
        modelMap.addAttribute("ticketDataList", ticketStatisticsByQuarters);
        return "admin/statistics/ticketByQuarter";
    }
    
    @GetMapping("/printTicketByQuarter")
    public void printTicketByQuarter(HttpServletResponse response) throws IOException {
        List<TicketStatisticsByQuarter> ticketStatisticsByQuarter = ticketService.countTicketByQuarter();
        generateExcel.createTicketByQuarterReport(ticketStatisticsByQuarter, response);
    }

    @GetMapping("/ticketPerOrder")
    public String gerStatisticsTicketPerOrder(ModelMap modelMap,
                                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }
        List<CountOrderOfQuantityTicket> countOrderOfQuantityTicket = ticketService.ticketPerOrder();
        modelMap.addAttribute("countOrderOfQuantityTicket", countOrderOfQuantityTicket);
        return "admin/statistics/ticketPerOder";
    }

    @GetMapping("/incomeByMonth")
    public String getStatisticsCostByMonth(ModelMap modelMap,
                                             @RequestParam(defaultValue = "2019") int year,
                                             @RequestParam(defaultValue = "5") int numberOfYear,
                                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }

        List<CostStatistics> costDataList = orderInfoService.statisticsCostByMonth();
        List<Integer> uniqueYears = ticketService.getUniqueYear();
        List<Integer> getNumberYearsFrom = ticketService.getNumberYearsFrom(year, numberOfYear);

        modelMap.addAttribute("costDataList", costDataList);
        modelMap.addAttribute("uniqueYears", uniqueYears);
        modelMap.addAttribute("getNumberYearsFrom", getNumberYearsFrom);
        modelMap.addAttribute("numberOfYear", numberOfYear);
        modelMap.addAttribute("year",year);
        return "admin/statistics/totalCostByMonth";
    }
    
    @GetMapping("/printIncomeByMonth")
    public void printIncomeByMonth(HttpServletResponse response) throws IOException {
        List<CostStatistics> costDataList = orderInfoService.statisticsCostByMonth();
        generateExcel.createCostByMonthReport(costDataList, response);
    }

    @GetMapping("/incomeByQuarter")
    public String getStatisticsCostByQuarter(ModelMap modelMap,
                                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }

        List<CostStatisticsByQuarter> costStatisticsByQuarters = orderInfoService.costStatisticsByQuarter();
        modelMap.addAttribute("costDataList", costStatisticsByQuarters);
        return "admin/statistics/totalCostByQuarter";
    }
    
    @GetMapping("/printIncomeByQuarter")
    public void printIncomeByQuarter(HttpServletResponse response) throws IOException {
        List<CostStatisticsByQuarter> costStatisticsByQuarter = orderInfoService.costStatisticsByQuarter();
        generateExcel.createCostByQuarterReport(costStatisticsByQuarter, response);
    }
}
