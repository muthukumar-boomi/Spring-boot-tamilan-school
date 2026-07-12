package springboot.tamilan_school;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class staff_record_con {

    @Autowired
    private staff_record_repo repo;

    // Map both /staff and /staff_record to the same page
    @GetMapping({"/staff", "/staff_record"})
    public String showStaffList(Model model) {
        List<staff_record_model> staffList = repo.get_all();
        model.addAttribute("staffList", staffList);
        return "staff_record";
    }

    @PostMapping("/saveStaffData")
    public String saveOrUpdateStaff(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("email") String email,
            @RequestParam("mobile") String mobile,
            @RequestParam("dep") String dep,
            @RequestParam("designation") String designation,
            @RequestParam("subject") String subject,
            @RequestParam("exp") String exp,
            RedirectAttributes ra) {
        try {
            staff_record_model existing = repo.get_by_id(id);
            if (existing == null) {
                repo.insert(id, name, gender, email, mobile, dep, designation, subject, exp);
                ra.addFlashAttribute("msg", "✅ Staff added successfully");
            } else {
                repo.update(id, name, gender, email, mobile, dep, designation, subject, exp);
                ra.addFlashAttribute("msg", "✏️ Staff updated successfully");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("err", "❌ Operation failed: " + e.getMessage());
        }
        return "redirect:/staff_record";
    }

    @GetMapping("/staff/delete/{id}")
    public String deleteStaff(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            repo.delete(id);
            ra.addFlashAttribute("msg", "🗑️ Staff deleted successfully");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/staff_record";
    }
}