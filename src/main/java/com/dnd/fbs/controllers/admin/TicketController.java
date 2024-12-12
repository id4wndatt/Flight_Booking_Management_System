package com.dnd.fbs.controllers.admin;

import com.dnd.fbs.models.Ticket;
import com.dnd.fbs.payload.TicketDto;
import com.dnd.fbs.repositories.FlightRepository;
import com.dnd.fbs.repositories.TicketRepository;
import com.dnd.fbs.services.AirlineCompanyServiceImpl;
import com.dnd.fbs.services.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/admin/ticketList")
    public String getFirstPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }
        return getAllTicket(model, request,1, 5, "ticketID", "asc");
    }

    @GetMapping("/admin/ticketList/page/{pageNo}")
    public String getAllTicket(Model model,
                               HttpServletRequest request,
                               @PathVariable(name = "pageNo") int pageNo,
                               @RequestParam(defaultValue = "5", required = false) int pageSize,
                               @RequestParam(defaultValue = "ticketID", required = false) String sortField,
                               @RequestParam(defaultValue = "asc", required = false) String sortDir) {
        try {
            HttpSession session = request.getSession();
            if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
                return "admin/loginAdmin";
            }

            Page<Ticket> tickets = ticketService.findAll(pageNo, pageSize, sortField, sortDir);

            long startCount = (long) (pageNo - 1) * pageSize + 1;
            long endCount = startCount + pageSize -1;
            if (endCount > tickets.getTotalElements()) {
                endCount = tickets.getTotalElements();
            }
            String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

            model.addAttribute("reverseSortDir", reverseSortDir);
            model.addAttribute("tickets", tickets);
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", tickets.getTotalPages());
            model.addAttribute("startCount", startCount);
            model.addAttribute("endCount", endCount);
            model.addAttribute("totalItems", tickets.getTotalElements());
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keyword", null);
            return "admin/ticket/TicketList";
        } catch (Exception e) {
            System.out.println(e);
            return "redirect:/admin/ticketList";
        }
    }


}
