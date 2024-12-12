package com.dnd.fbs.controllers.admin;

import com.dnd.fbs.AbstractClass;
import com.dnd.fbs.models.AirlineCompany;
import com.dnd.fbs.models.Flight;
import com.dnd.fbs.models.Plane;
import com.dnd.fbs.models.User;
import com.dnd.fbs.repositories.AirlineCompanyRepository;
import com.dnd.fbs.repositories.FlightRepository;
import com.dnd.fbs.repositories.PlaneRepository;
import com.dnd.fbs.repositories.UserRepositories;
import com.dnd.fbs.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController extends AbstractClass {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private PlaneRepository planeRepository;
    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/admin/api")
    public String getAdmin(){
        return "admin/api";
    }

    @GetMapping("/admin")
    public String getAdminUrl(HttpServletRequest request,ModelMap modelMap){
        HttpSession session = request.getSession();
        if(session.getAttribute("role")=="1" || session.getAttribute("role")==null){
            return "admin/loginAdmin";
        }
        List<Plane> planes = planeRepository.findAll();
        List<AirlineCompany> airlineCompanies = airlineCompanyRepository.findAll();
        List<User> users = userRepositories.findAll();
        modelMap.addAttribute("numberOfAircraft", planes.size());
        modelMap.addAttribute("numberOfAirlineCompany", airlineCompanies.size());
        modelMap.addAttribute("numberOfUserAccount", users.size());
        return "/admin/adminIndex";
    }

    @GetMapping("/admin/login")
    public String getLoginAdmin(HttpServletRequest request){
        return "/admin/loginAdmin";
    }

    @PostMapping("/admin/login")
    public String loginAdmin(HttpServletRequest request, RedirectAttributes redirectAttributes){
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username);
            System.out.println(password);
            HttpSession session = request.getSession();
            User user = userServiceImpl.getAccount(username);
            if(isNullorEmpty(username) || isNullorEmpty(password)){
                redirectAttributes.addFlashAttribute("message", "Vui lòng nhập tên người dùng và mật khẩu");
                return "redirect:/admin/login";
            }
            if(user == null){
                redirectAttributes.addFlashAttribute("message", "Tài khoản quản trị không tồn tại");
                return "redirect:/admin/login";
            }
            if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
                redirectAttributes.addFlashAttribute("message", "Mật khẩu không chính xác");
                return "redirect:/admin/login";
            }
            if(user.getRole()<2) {
                redirectAttributes.addFlashAttribute("message", "Truy cập bị từ chối");
                return "redirect:/admin/login";
            }
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("role", user.getRole());
            session.setAttribute("user", user);
            session.setAttribute("username", username);
            session.setAttribute("role", user.getRole());
//            return "redirect:/admin";
            return "/admin/adminIndex";
        } catch (Exception exception){
            System.out.println(exception);
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("user");
        session.removeAttribute("role");
        return "redirect:/admin/login";
    }

}
