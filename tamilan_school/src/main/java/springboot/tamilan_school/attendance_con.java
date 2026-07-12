package springboot.tamilan_school;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class attendance_con {

    @Autowired
    private attendance_repo repo;
    
    @Autowired
    private student_record_repo studentRepo;   // To fetch student details for auto-fill

    // Show attendance list page
    @GetMapping("/attendance")
    public String showAttendance(Model model) {
        List<attendance_model> list = repo.get_all();
        model.addAttribute("attendance", list);
        return "attendance";
    }

    // Save or update attendance (insert/update)
    @PostMapping("/saveAttendanceData")
    public String saveOrUpdate(@RequestParam("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("date") String date,
                               @RequestParam("gender") String gender,
                               @RequestParam("classs") String classs,
                               @RequestParam("section") String section,
                               @RequestParam("status") String status,
                               RedirectAttributes ra) {
        try {
            attendance_model existing = repo.get_by_id(id);
            if (existing == null) {
                repo.insert(id, name, date, gender, classs, section, status);
                ra.addFlashAttribute("msg", "✅ Attendance record added");
            } else {
                repo.update(id, name, date, gender, classs, section, status);
                ra.addFlashAttribute("msg", "✏️ Attendance record updated");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("err", "❌ Operation failed: " + e.getMessage());
        }
        return "redirect:/attendance";
    }

    // Delete attendance record
    @GetMapping("/attendance/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        try {
            repo.delete(id);
            ra.addFlashAttribute("msg", "🗑️ Attendance record deleted");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/attendance";
    }
    
    // REST endpoint for auto-filling student details from student_record table
    @GetMapping("/getStudentData/{rollId}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getStudentData(@PathVariable String rollId) {
        student_record_model student = studentRepo.get_by_id(rollId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, String> data = new HashMap<>();
        data.put("name", student.getName());
        data.put("gender", student.getGender());
        data.put("classs", student.getClasss());
        data.put("section", student.getSection());
        return ResponseEntity.ok(data);
    }
}