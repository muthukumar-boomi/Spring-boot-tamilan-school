package springboot.tamilan_school;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class timetable_con {

    @Autowired
    private timetable_repo repo;

    @GetMapping("/timetable")
    public String showTimetable(Model model) {
        List<timetable_model> list = repo.get_all();
        model.addAttribute("timetable", list);
        return "timetable";
    }

    @PostMapping("/saveTimetableData")
    public String saveOrUpdate(@RequestParam("id") String id,
                               @RequestParam("classs") String classs,
                               @RequestParam("section") String section,
                               @RequestParam("subject") String subject,
                               @RequestParam("staff") String staff,
                               @RequestParam("day") String day,
                               @RequestParam("stime") String stime,
                               @RequestParam("etime") String etime,
                               RedirectAttributes ra) {
        try {
            timetable_model existing = repo.get_by_id(id);
            if (existing == null) {
                repo.insert(id, classs, section, subject, staff, day, stime, etime);
                ra.addFlashAttribute("msg", "✅ Timetable entry added");
            } else {
                repo.update(id, classs, section, subject, staff, day, stime, etime);
                ra.addFlashAttribute("msg", "✏️ Timetable entry updated");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("err", "❌ Operation failed: " + e.getMessage());
        }
        return "redirect:/timetable";
    }

    @GetMapping("/timetable/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        try {
            repo.delete(id);
            ra.addFlashAttribute("msg", "🗑️ Timetable entry deleted");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/timetable";
    }
}