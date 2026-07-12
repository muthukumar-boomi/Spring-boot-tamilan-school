package springboot.tamilan_school;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class fees_con {

    @Autowired
    private fees_repo repo;

    @GetMapping("/fees")
    public String showList(Model model) {
        List<fees_model> list = repo.get_all();
        // Calculate balance
        for (fees_model f : list) {
            try {
                BigDecimal total = new BigDecimal(f.getRs());
                BigDecimal paid = (f.getPamount() == null || f.getPamount().isEmpty()) 
                                    ? BigDecimal.ZERO : new BigDecimal(f.getPamount());
                f.setBalance(total.subtract(paid));
            } catch(Exception e) {
                f.setBalance(BigDecimal.ZERO);
            }
        }
        System.out.println("Total records: " + list.size());
        model.addAttribute("fees", list);
        return "fees";
    }

    @PostMapping("/saveFeesData")
    public String saveOrUpdate(@RequestParam("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("ftype") String ftype,
                               @RequestParam("ddate") String ddate,
                               @RequestParam("rs") String rs,
                               @RequestParam(value = "pdate", required = false) String pdate,
                               @RequestParam(value = "pmode", required = false) String pmode,
                               @RequestParam(value = "pamount", required = false) String pamount,
                               @RequestParam("status") String status,
                               RedirectAttributes ra) {
        try {
            fees_model existing = repo.get_by_id(id);
            if (existing == null) {
                repo.insert(id, name, ftype, ddate, rs, pdate, pmode, pamount, status);
                ra.addFlashAttribute("msg", "✅ Fee added");
            } else {
                repo.update(id, name, ftype, ddate, rs, pdate, pmode, pamount, status);
                ra.addFlashAttribute("msg", "✏️ Fee updated");
            }
        } catch(Exception e) {
            ra.addFlashAttribute("err", e.getMessage());
        }
        return "redirect:/fees";
    }

    @GetMapping("/fees/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        try {
            repo.delete(id);
            ra.addFlashAttribute("msg", "🗑️ Deleted");
        } catch(Exception e) {
            ra.addFlashAttribute("err", e.getMessage());
        }
        return "redirect:/fees";
    }
}