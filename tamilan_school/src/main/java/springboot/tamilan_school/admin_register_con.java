package springboot.tamilan_school;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class admin_register_con {
    
    @Autowired
    private admin_register_repo ar;
    
  
    
    @GetMapping("/welcome_page")
    public String show() { 
        return "welcome_page"; 
    }
    
    // NEW: handle /welcome as well
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome_page";
    }
    
    @GetMapping("/admin_register")
    public String admin_register() { 
        return "admin_register"; 
    }
    
    @GetMapping("/")
    public String dashboard() {
        return "dashboard";   // serves the same admin page
    }
    
    
    @PostMapping("/admin_register")
    public String insert_admin_register(@RequestParam("id") String id, 
                                   @RequestParam("name") String name, 
                                   @RequestParam("pass") String pass, 
                                   @RequestParam("dep") String dep, 
                                   Model m) {
        try {
            // Check if user exists by ID
            Optional<admin_register_model> userExists = ar.findById(id);
            if(userExists.isPresent()) {
                m.addAttribute("err", "User ID already exists");
                return "admin_register";
            }
            
            String epass = en_de.encrypt(pass);
            ar.insert(id, name, epass, dep);
            m.addAttribute("msg", "Registration successful");
        } catch(Exception e) {
            m.addAttribute("err", "Registration failed: " + e.getMessage());
        }
        return "admin_register";
    }
    
    @GetMapping("/admin_login")
    public String admin_login() { 
        return "admin_login"; 
    }
    
    @PostMapping("/admin_login")
    public String admin_login(@RequestParam("id") String id, 
                         @RequestParam("pass") String password, 
                         @RequestParam("dep") String dep,
                         Model model) {
        try {
            Optional<admin_register_model> adminOptional = ar.findById(id);
            if(!adminOptional.isPresent()) {
                model.addAttribute("err", "Invalid User ID");
                return "admin_login";
            }
            
            admin_register_model admin = adminOptional.get();
            String decryptedPass = en_de.decrypt(admin.getPass());
            
            if(decryptedPass != null && decryptedPass.equals(password)) {
                model.addAttribute("msg", "Login successful");
                return "mainpage";
            } else {
                model.addAttribute("err", "Invalid password");
                return "admin_login";
            }
        } catch(Exception e) {
            model.addAttribute("err", "Login failed: " + e.getMessage());
            return "admin_login";
        }
    }
}