package springboot.tamilan_school;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class admission_con {

    @Autowired
    private admission_repo repo;

    @GetMapping("/admission")
    public String showAdmission(Model model) {
        List<admission_model> list = repo.get_all();
        model.addAttribute("admission", list);
        return "admission";
    }

    @PostMapping("/saveAdmissionData")
    public String saveOrUpdate(@RequestParam("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("date") String date,
                               @RequestParam("dob") String dob,
                               @RequestParam("gender") String gender,
                               @RequestParam("classs") String classs,
                               @RequestParam("pschool") String pschool,
                               @RequestParam("accommodation") String accommodation,
                               @RequestParam("fname") String fname,
                               @RequestParam("mname") String mname,
                               @RequestParam("mobile") String mobile,
                               @RequestParam("address") String address,
                               RedirectAttributes ra) {
        try {
            admission_model existing = repo.get_by_id(id);
            if (existing == null) {
                repo.insert(id, name, date, dob, gender, classs, pschool, accommodation, fname, mname, mobile, address);
                ra.addFlashAttribute("msg", "Admission record added");
            } else {
                repo.update(id, name, date, dob, gender, classs, pschool, accommodation, fname, mname, mobile, address);
                ra.addFlashAttribute("msg", " Admission record updated");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("err", " Operation failed: " + e.getMessage());
        }
        return "redirect:/admission";
    }

    @GetMapping("/admission/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        try {
            repo.delete(id);
            ra.addFlashAttribute("msg", " Admission record deleted");
        } catch (Exception e) {
            ra.addFlashAttribute("err", "Delete failed: " + e.getMessage());
        }
        return "redirect:/admission";
    }

    // REST endpoint for auto‑fill in student form – now sends "classs"
    @GetMapping("/getAdmissionData/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getAdmissionData(@PathVariable String id) {
        admission_model admission = repo.get_by_id(id);
        if (admission == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, String> data = new HashMap<>();
        data.put("id", admission.getId());
        data.put("name", admission.getName());
        data.put("gender", admission.getGender());
        data.put("dob", admission.getDob());
        // ✅ FIXED: send "classs" (matches the HTML JavaScript expectation)
        data.put("classs", admission.getClasss());
        data.put("mobile", admission.getMobile());
        data.put("address", admission.getAddress());
        return ResponseEntity.ok(data);
    }
}