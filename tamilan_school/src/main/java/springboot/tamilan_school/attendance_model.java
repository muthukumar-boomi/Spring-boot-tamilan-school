package springboot.tamilan_school;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class attendance_model {
    
    @Id
    private String id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private String date;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "classs")
    private String classs;
    
    @Column(name = "section")
    private String section;
    
    @Column(name = "status")
    private String status;

    public attendance_model() {}
    
    public attendance_model(String id, String name, String date, String gender, 
                            String classs, String section, String status) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.classs = classs;
        this.section = section;
        this.status = status;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getClasss() { return classs; }
    public void setClasss(String classs) { this.classs = classs; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}