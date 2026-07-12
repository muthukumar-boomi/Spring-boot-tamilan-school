package springboot.tamilan_school;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class student_record_con {

    @Autowired
    private student_record_repo studentRepo;

    @Autowired
    private admission_repo admissionRepo;

    @GetMapping("/student_record")
    public String showStudentList(Model model) {
        List<student_record_model> students = studentRepo.get_all();
        model.addAttribute("students", students);
        return "student_record";
    }

    @PostMapping("/saveStudentData")
    public String saveOrUpdateStudent(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("email") String email,
            @RequestParam("dob") String dob,
            @RequestParam(value = "blood", required = false) String blood,
            @RequestParam("classs") String classs,
            @RequestParam("section") String section,
            @RequestParam(value = "grade", required = false) String grade,
            @RequestParam("pname") String pname,
            @RequestParam("mobile") String mobile,
            @RequestParam("address") String address,
            RedirectAttributes ra) {

        try {
            // Check if admission exists (optional – only for validation)
            admission_model admission = admissionRepo.get_by_id(id);
            if (admission == null) {
                ra.addFlashAttribute("err", " No admission found with ID: " + id + ". Please add admission first.");
                return "redirect:/student_record";
            }

            // Use the values directly from the form (user can edit them)
            student_record_model existing = studentRepo.get_by_id(id);
            if (existing == null) {
                studentRepo.insert(id, name, gender, email, dob, blood, classs, section, grade, pname, mobile, address);
                ra.addFlashAttribute("msg", "Student record added successfully.");
            } else {
                studentRepo.update(id, name, gender, email, dob, blood, classs, section, grade, pname, mobile, address);
                ra.addFlashAttribute("msg", "Student record updated successfully.");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Operation failed: " + e.getMessage());
        }
        return "redirect:/student_record";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            studentRepo.delete(id);
            ra.addFlashAttribute("msg", "Student record deleted");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/student_record";
    }
}