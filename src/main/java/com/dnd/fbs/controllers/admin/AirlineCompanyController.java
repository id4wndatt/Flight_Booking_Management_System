package com.dnd.fbs.controllers.admin;

import com.dnd.fbs.FileUploadUtil;
import com.dnd.fbs.exception.AirlineCompanyNotFoundException;
import com.dnd.fbs.models.AirlineCompany;
import com.dnd.fbs.services.AirlineCompanyService;
import com.dnd.fbs.services.AirlineCompanyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AirlineCompanyController {
    private AirlineCompanyService as;

    public AirlineCompanyController(AirlineCompanyService as) {
        this.as = as;
    }

    @GetMapping("/airlineCompanies")
    public String listFirstPage(Model model) {
        return listByPage(model, 1, "airlineID", "asc", null);
    }

    @GetMapping("/airlineCompanies/page/{pageNum}")
    public String listByPage(Model model,
                             @PathVariable(name = "pageNum") int pageNum,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword) {
        Page<AirlineCompany> page = as.listByPage(pageNum, sortField, sortDir, keyword);
        List<AirlineCompany> listAirlineCompany =page.getContent();

        long startCount = (long) (pageNum - 1) * AirlineCompanyServiceImpl.AIRLINE_COMPANY_PER_PAGE + 1;
        long endCount = startCount + AirlineCompanyServiceImpl.AIRLINE_COMPANY_PER_PAGE -1;
        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

        model.addAttribute("reverseSortDir", reverseSortDir);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listAirlineCompany", listAirlineCompany);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        return "admin/airlineCompany/airlineCompanyList";
    }

    @GetMapping("/airlineCompanies/new")
    public String newAirlineCompany(Model model) {

        AirlineCompany airlineCompany = new AirlineCompany();

        model.addAttribute("airlineCompany", airlineCompany);
        model.addAttribute("pageTitle", "Add new airlineCompany");
        return "admin/airlineCompany/airlineCompany_form";
    }

    @PostMapping("/airlineCompanies/save")
    public String saveAirlineCompany(AirlineCompany airlineCompany,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("image") MultipartFile multipartFile
    ) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            airlineCompany.setAirlineLogo(fileName);
            AirlineCompany savedAC = as.save(airlineCompany);
            String uploadDir = "airlineCompany-photos/" + savedAC.getAirlineID();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            if (airlineCompany.getAirlineLogo().isEmpty()) airlineCompany.setAirlineLogo(null);
            as.save(airlineCompany);
        }

        redirectAttributes.addFlashAttribute("message", "Hãng hàng không đã được lưu thành công !");

        return getRedirectURLtoAfterAirlineCompany(airlineCompany);
    }

    private static String getRedirectURLtoAfterAirlineCompany(AirlineCompany airlineCompany) {
        String airlineName = airlineCompany.getAirlineName();
        return "redirect:/admin/airlineCompanies/page/1?sortField=airlineID&sortDir=asc&keyword=" + airlineName;
    }

    @GetMapping("/airlineCompanies/edit/{id}")
    public String editAirlineCompany(@PathVariable(name = "id") Integer id,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            AirlineCompany airlineCompany = as.get(id);

            model.addAttribute("airlineCompany", airlineCompany);
            model.addAttribute("pageTitle", "Edit airlineCompany (ID: " + id + ")");

            return "admin/airlineCompany/airlineCompany_form";
        } catch (AirlineCompanyNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/airlineCompanies";
        }
    }

    @GetMapping("/airlineCompanies/delete/{id}")
    public String deleteAirline(@PathVariable(name = "id") Integer id,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        try {
            as.deleteById(id);

            String acDir = "./airlineCompany-photos/" + id;
            FileUploadUtil.removeDir(acDir);
            redirectAttributes.addFlashAttribute("message", "Mã số Hãng hàng không là: " + id +
                    " has been deleted successfully");
        } catch (AirlineCompanyNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/admin/airlineCompanies";
    }
}
