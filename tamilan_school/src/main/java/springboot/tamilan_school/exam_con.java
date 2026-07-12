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
public class exam_con {

    @Autowired
    private exam_repo repo;

    @GetMapping("/exam")
    public String showExam(Model model) {
        List<exam_model> list = repo.get_all();
        model.addAttribute("exam", list);
        return "exam";
    }

    @PostMapping("/saveExamData")
    public String saveOrUpdate(@RequestParam("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("etype") String etype,
                               @RequestParam("subject") String subject,
                               @RequestParam("date") String date,
                               @RequestParam("time") String time,
                               @RequestParam("section") String section,
                               @RequestParam("tmark") String tmark,
                               @RequestParam("mark") String mark,
                               @RequestParam("grade") String grade,
                               @RequestParam("status") String status,
                               RedirectAttributes ra) {
        try {
            exam_model existing = repo.get_by_id(id);
            if (existing == null) {
                repo.insert(id, name, etype, subject, date, time, section, tmark, mark, grade, status);
                ra.addFlashAttribute("msg", "✅ Exam record added");
            } else {
                repo.update(id, name, etype, subject, date, time, section, tmark, mark, grade, status);
                ra.addFlashAttribute("msg", "✏️ Exam record updated");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("err", "❌ Operation failed: " + e.getMessage());
        }
        return "redirect:/exam";
    }

    @GetMapping("/exam/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        try {
            repo.delete(id);
            ra.addFlashAttribute("msg", "🗑️ Exam record deleted");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/exam";
    }
}