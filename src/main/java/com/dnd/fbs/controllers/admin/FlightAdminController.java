package com.dnd.fbs.controllers.admin;

import com.dnd.fbs.models.Flight;
import com.dnd.fbs.services.FlightService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FlightAdminController {
	@Autowired
    private FlightService flightService;

    public FlightAdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/admin/flightList")
    public String getFirstPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }
        return getAllFlights(model, request,1, 5, "flightID", "asc");
    }

    @GetMapping("/admin/flightList/page/{pageNo}")
    public String getAllFlights(Model model,
                                HttpServletRequest request,
                                @PathVariable(name = "pageNo") int pageNo,
                                @RequestParam(defaultValue = "5", required = false) int pageSize,
                                @RequestParam(defaultValue = "flightID", required = false) String sortField,
                                @RequestParam(defaultValue = "asc", required = false) String sortDir
    ) {
        try {
            HttpSession session = request.getSession();
            if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
                return "admin/loginAdmin";
            }
            Page<Flight> flights = flightService.findAll(pageNo, pageSize, sortField, sortDir);
            long startCount = (long) (pageNo - 1) * pageSize + 1;
            long endCount = startCount + pageSize -1;
            if (endCount > flights.getTotalElements()) {
                endCount = flights.getTotalElements();
            }
            String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

            model.addAttribute("reverseSortDir", reverseSortDir);
            model.addAttribute("flights", flights);
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", flights.getTotalPages());
            model.addAttribute("startCount", startCount);
            model.addAttribute("endCount", endCount);
            model.addAttribute("totalItems", flights.getTotalElements());
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keyword", null);
            return "admin/flight/FlightList";
        } catch (Exception e) {
            return "redirect:/admin/flightList";
        }
    }

    @GetMapping("admin/addFlight")
    public String getAddFlight(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }
        return "admin/flight/AddFlight";
    }

    @PostMapping("admin/addFlight")
    public String addFlight(@ModelAttribute("flight") Flight flight,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) {
    	
    	flight.setDeparting_from(request.getParameter("departingForm"));
        flight.setArriving_at(request.getParameter("arrivingAt"));
        flight.setDate_flight(request.getParameter("dateFlight"));
        flight.setFlight_time(request.getParameter("flightTime"));
        flight.setDeparture_time(request.getParameter("departureTime"));
        String feeFlightx = request.getParameter("feeFlightx");
        try {
            if(flight.getDeparting_from().trim().equals("")){
                redirectAttributes.addFlashAttribute("departingForm", "Điểm cất cánh không được để trống");
                return "redirect:/admin/addFlight";
            }
            if(flight.getArriving_at().trim().equals("")) {
                redirectAttributes.addFlashAttribute("arrivingAt", "Điểm hạ cánh không được để trống");
                return "redirect:/admin/addFlight";
            }
            if(flight.getDate_flight().equals("")) {
                redirectAttributes.addFlashAttribute("dateFlight", "Ngày chuyến bay không được để trống");
                return "redirect:/admin/addFlight";
            }
            if(flight.getFlight_time().equals("")) {
                redirectAttributes.addFlashAttribute("flightTime", "Thời gian hạ cánh không được để trống");
                return "redirect:/admin/addFlight";
            }
            if(flight.getDeparture_time().equals("")) {
                redirectAttributes.addFlashAttribute("departureTime", "Thời gian cất cánh không được để trống");
                return "redirect:/admin/addFlight";
            }
            if(feeFlightx.equals("")) {
                redirectAttributes.addFlashAttribute("feeFlight", "Phí chuyến bay không được để trống");
                return "redirect:/admin/addFlight";
            } else if(Integer.parseInt(feeFlightx)<=0) {
                redirectAttributes.addFlashAttribute("feeFlight", "Phí chuyến bay phải >= 0");
                return "redirect:/admin/addFlight";
            }
            flight.setFee_flight(Long.parseLong(feeFlightx));
            flightService.save(flight);
            redirectAttributes.addFlashAttribute("message", "Tạo chuyến bay thành công !");
            return "redirect:/admin/flightList";
        } catch (Exception e) {
            return "admin/flight/AddFlight";
        }
    }

    @GetMapping("admin/updateFlight/{flightId}")
    public String updateFlight(@PathVariable Integer flightId,
                               ModelMap modelMap,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }
        modelMap.addAttribute("flight", flightService.findById(flightId).get());
        return "admin/flight/UpdateFlight";
    }

    @PostMapping("admin/updateFlight/{flightId}")
    public String updateFlight(@ModelAttribute("flight") Flight flight,
                               ModelMap modelMap,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes,
                               @PathVariable Integer flightId
    ){
    	flight.setDeparting_from(request.getParameter("departingForm"));
        flight.setArriving_at(request.getParameter("arrivingAt"));
        flight.setDate_flight(request.getParameter("dateFlight"));
        flight.setFlight_time(request.getParameter("flightTime"));
        flight.setDeparture_time(request.getParameter("departureTime"));
        String feeFlightx = request.getParameter("feeFlightx");
        try {
            if(flightService.findById(flightId).isPresent()) {
                Flight foundFlight = flightService.findById(flightId).get();
                if(flight.getDeparting_from().trim().equals("")){
                    redirectAttributes.addFlashAttribute("departingForm", "Điểm cất cánh không được để trống");
                    return "redirect:/admin/updateFlight/{flightId}";
                }

                if(flight.getArriving_at().trim().equals("")) {
                    redirectAttributes.addFlashAttribute("arrivingAt", "Điểm hạ cánh không được để trống");
                    return "redirect:/admin/updateFlight/{flightId}";
                }
                if(flight.getDate_flight().trim().equals("")) {
                    redirectAttributes.addFlashAttribute("dateFlight", "Ngày chuyến bay không được để trống");
                    return "redirect:/admin/updateFlight/{flightId}";
                }
                if(flight.getFlight_time().trim().equals("")) {
                    redirectAttributes.addFlashAttribute("flightTime", "Thời gian hạ cánh không được để trống");
                    return "redirect:/admin/updateFlight/{flightId}";
                }
                if(flight.getDeparture_time().trim().equals("")) {
                    redirectAttributes.addFlashAttribute("departureTime", "Thời gian cất cánh không được để trống");
                    return "redirect:/admin/updateFlight/{flightId}";
                }
                if(feeFlightx.equals("")) {
                    redirectAttributes.addFlashAttribute("feeFlight", "Phí chuyến bay không được để trống");
                    return "redirect:/admin/updateFlight/{flightId}";
                } else if(Integer.parseInt(feeFlightx)<=0) {
                    redirectAttributes.addFlashAttribute("feeFlight", "Phí chuyến bay phải >= 0");
                    return "redirect:/admin/updateFlight/{flightId}";
                }
                foundFlight.setDeparting_from(flight.getDeparting_from());
                foundFlight.setArriving_at(flight.getArriving_at());
                foundFlight.setFlight_time(flight.getFlight_time());
                foundFlight.setDeparture_time(flight.getDeparture_time());
                foundFlight.setFee_flight(Long.parseLong(feeFlightx));
                flightService.save(foundFlight);
            }
            redirectAttributes.addFlashAttribute("message", "Lưu chuyến bay thành công !");
            return "redirect:/admin/flightList";
        } catch (Exception e) {
            System.out.println(e);
            return "redirect:/admin/updateFlight/{flightId}";
        }
    }

    @PostMapping("admin/deleteFlight/{flightId}")
    public String deleteFlight(@PathVariable int flightId, RedirectAttributes redirectAttributes) {
        flightService.deleteById(flightId);
        redirectAttributes.addFlashAttribute("message", "Xoá chuyến bay thành công !");
        return "redirect:/admin/flightList";
    }

    @GetMapping("admin/flight/search")
    public String searchFlight(@RequestParam(defaultValue = "", required = false) String search, ModelMap modelMap) {
        List<Flight> flights = flightService.searchFlights(search);
        modelMap.addAttribute("flights", flights);
        modelMap.addAttribute("query", search);
        return "admin/flight/SearchFlight";
    }
}
