package com.dnd.fbs.controllers.admin;

import com.dnd.fbs.AbstractClass;
import com.dnd.fbs.models.AirlineCompany;
import com.dnd.fbs.models.Airline_company;
import com.dnd.fbs.models.Plane;
import com.dnd.fbs.services.AirlineCompanyService;
import com.dnd.fbs.services.PlaneService;
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
public class PlaneController extends AbstractClass {
	
	@Autowired
    private PlaneService planeService;
	
	@Autowired
    private AirlineCompanyService airlineCompanyService;

    public PlaneController(PlaneService planeService, AirlineCompanyService airlineCompanyService) {
        this.planeService = planeService;
        this.airlineCompanyService = airlineCompanyService;
    }

    @GetMapping("/admin/listPlane")
    public String getFirstPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }
        return getAllPlane(model, request,1, 5, "planeID", "asc");
    }

    @GetMapping("/admin/listPlane/page/{pageNo}")
    public String getAllPlane(Model model,
                              HttpServletRequest request,
                              @PathVariable(name = "pageNo") int pageNo,
                              @RequestParam(defaultValue = "5", required = false) int pageSize,
                              @RequestParam(defaultValue = "planeID", required = false) String sortField,
                              @RequestParam(defaultValue = "ASC", required = false) String sortDir
    ) {
        HttpSession session = request.getSession();
        if((int) session.getAttribute("role") < 2 || session.getAttribute("role")==null){
            return "redirect:/admin/login";
        }
        try {

            Page<Plane> planes = planeService.findAll(pageNo, pageSize, sortField, sortDir);
            long startCount = (long) (pageNo - 1) * pageSize + 1;
            long endCount = startCount + pageSize -1;
            if (endCount > planes.getTotalElements()) {
                endCount = planes.getTotalElements();
            }
            String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

            model.addAttribute("reverseSortDir", reverseSortDir);
            model.addAttribute("planes", planes);
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", planes.getTotalPages());
            model.addAttribute("startCount", startCount);
            model.addAttribute("endCount", endCount);
            model.addAttribute("totalItems", planes.getTotalElements());
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keyword", null);
            return "admin/plane/ListPlanes";
        } catch(Exception e) {
            return "redirect:/admin/listPlane";
        }
    }

    @GetMapping("/admin/addPlane")
    public String getAddPlane(@ModelAttribute("airlineCompany") AirlineCompany airlineCompany,
                              ModelMap modelMap,
                              HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        if((int) session.getAttribute("role") < 2 || session.getAttribute("role")==null){
            return "redirect:/admin/login";
        }
        Iterable<AirlineCompany> airlineCompanies = airlineCompanyService.findAll();
        modelMap.addAttribute("airlineCompanies", airlineCompanies);
        return "admin/plane/AddPlane";
    }

    @PostMapping("/admin/addPlane")
    public String addPlane(@ModelAttribute("plane") Plane plane,
                              ModelMap modelMap,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request) {
    	plane.setPlane_name(request.getParameter("planeName"));
        String slSeat = request.getParameter("slSeat");
        String airlineID = request.getParameter("airlineID");
        
        System.out.println("Plane Name: " + plane.getPlane_name());
        System.out.println("Seat Quantity: " + slSeat);
        System.out.println("Airline ID: " + airlineID);
        
        try{
            if(isNullorEmpty(plane.getPlane_name().trim())) {
                redirectAttributes.addFlashAttribute("planeName", "planeName is cannot be null");
                return "redirect:/admin/addPlane";
            }
            if(slSeat.isEmpty() || slSeat.equals(null) || slSeat.equals("")) {
                redirectAttributes.addFlashAttribute("quantitySeat", "quantitySeat is cannot be null");
                return "redirect:/admin/addPlane";
            }
            if(Integer.parseInt(slSeat)<1) {
                redirectAttributes.addFlashAttribute("quantitySeat", "quantitySeat is higher 0");
                return "redirect:/admin/addPlane";
            }
            
            plane.setQuantity(Integer.parseInt(slSeat));
            
            Airline_company airlineCompany = new Airline_company();
            airlineCompany.setAirlineID(Integer.parseInt(airlineID));
            plane.setAirlineCompany(airlineCompany);
           
            planeService.save(plane);
            redirectAttributes.addFlashAttribute("message", "Tạo máy bay thành công !");

            Iterable<Plane> planes = planeService.findAll();
            modelMap.addAttribute("planes", planes);
            return "redirect:/admin/listPlane";
        } catch (Exception e) {
        	e.printStackTrace();
            Iterable<AirlineCompany> airlineCompanies = airlineCompanyService.findAll();
            modelMap.addAttribute("airlineCompanies", airlineCompanies);
            return "admin/plane/AddPlane";
        }
        
    }

    @GetMapping("/admin/updatePlane/{planeID}")
    public String getUpdatePlane(ModelMap modelMap,
                                 @PathVariable Integer planeID,
                                 HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        if((int) session.getAttribute("role") < 2 || session.getAttribute("role")==null){
            return "redirect:/admin/login";
        }

        Iterable<AirlineCompany> airlineCompanies = airlineCompanyService.findAll();
        modelMap.addAttribute("airlineCompanies", airlineCompanies);
        modelMap.addAttribute("plane", planeService.findById(planeID).get());
        return "admin/plane/UpdatePlane";
    }

    @PostMapping("/admin/updatePlane/{planeID}")
    public String updatePlane(@PathVariable Integer planeID,
                              @ModelAttribute("plane") Plane plane,
                              ModelMap modelMap,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes
    ) {
    	plane.setPlane_name(request.getParameter("planeName"));
        String airlineID = request.getParameter("airlineID");
        String slSeat = request.getParameter("slSeat");
        if (slSeat.isEmpty()) {
            slSeat="0";
        }
        try{
            if(planeService.findById(planeID).isPresent()) {
                Plane foundPlane = planeService.findById(plane.getPlaneID()).get();

                if(!isNullorEmpty(plane.getPlane_name().trim())) {
                    foundPlane.setPlane_name(plane.getPlane_name());
                }
                else if(isNullorEmpty(plane.getPlane_name().trim())) {
                    redirectAttributes.addFlashAttribute("planeName", "planeName is cannot be null");
                    return "redirect:/admin/updatePlane/{planeID}";
                }

                if(Integer.parseInt(slSeat)>0) {
                    foundPlane.setQuantity(Integer.parseInt(slSeat));
                }
                else if(Integer.parseInt(slSeat)<1) {
                    redirectAttributes.addFlashAttribute("quantitySeat", "quantitySeat is higher 0");
                    return "redirect:/admin/updatePlane/{planeID}";
                }

                if(plane.getAirlineCompany() != null && plane.getAirlineCompany().getAirlineID()>0) {
                    foundPlane.setAirlineCompany(plane.getAirlineCompany());

                	Airline_company airlineCompany = new Airline_company();
                    airlineCompany.setAirlineID(Integer.parseInt(airlineID));
                    foundPlane.setAirlineCompany(airlineCompany);
                }
                
                planeService.save(foundPlane);
                redirectAttributes.addFlashAttribute("message", "Lưu máy bay thành công !");
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return "redirect:/admin/updatePlane/{planeID}";
        }

        Iterable<Plane> planes = planeService.findAll();
        modelMap.addAttribute("planes", planes);
        return "redirect:/admin/listPlane";
    }

    @PostMapping("/admin/deletePlane/{planeID}")
    public String deletePlane(@PathVariable Integer planeID,
                              @ModelAttribute("plane") Plane plane,
                              ModelMap modelMap, RedirectAttributes redirectAttributes) {
        planeService.deleteById(plane.getPlaneID());
        redirectAttributes.addFlashAttribute("message", "Xoá chuyến bay thành công !");
        Iterable<Plane> planes = planeService.findAll();
        modelMap.addAttribute("planes", planes);
        return "redirect:/admin/listPlane";
    }

}
