package springboot.tamilan_school;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "timetable")
public class timetable_model {
    
    @Id
    private String id;
    
    @Column(name = "classs")
    private String classs;
    
    @Column(name = "section")
    private String section;
    
    @Column(name = "subject")
    private String subject;
    
    @Column(name = "staff")
    private String staff;
    
    @Column(name = "day")
    private String day;
    
    @Column(name = "stime")
    private String stime;
    
    @Column(name = "etime")
    private String etime;
    
    public timetable_model() {}
    
    public timetable_model(String id, String classs, String section, String subject,
                           String staff, String day, String stime, String etime) {
        this.id = id;
        this.classs = classs;
        this.section = section;
        this.subject = subject;
        this.staff = staff;
        this.day = day;
        this.stime = stime;
        this.etime = etime;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getClasss() { return classs; }
    public void setClasss(String classs) { this.classs = classs; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getStaff() { return staff; }
    public void setStaff(String staff) { this.staff = staff; }
    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }
    public String getStime() { return stime; }
    public void setStime(String stime) { this.stime = stime; }
    public String getEtime() { return etime; }
    public void setEtime(String etime) { this.etime = etime; }
}