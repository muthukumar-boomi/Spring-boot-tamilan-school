package springboot.tamilan_school;

import jakarta.persistence.*;

@Entity
@Table(name = "admission")
public class admission_model {
    
    @Id
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "dob")
    private String dob;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "classs")
    private String classs;
    
    @Column(name = "pschool")
    private String pschool;
    
    @Column(name = "accommodation")
    private String accommodation;
    
    @Column(name = "fname")
    private String fname;
    
    @Column(name = "mname")
    private String mname;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "address")
    private String address;
    
    public admission_model() {}
    
    public admission_model(String id, String name, String date, String dob, String gender, 
                         String classs, String pschool, String fname, String mname, String accommodation,
                         String mobile, String address) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.dob = dob;
        this.gender = gender;
        this.classs = classs;
        this.pschool = pschool;
        this.accommodation = accommodation;
        this.fname = fname;
        this.mname = mname;
        this.mobile = mobile;
        this.address = address;
    }

    // Getters and Setters (all fields)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getClasss() { return classs; }
    public void setClasss(String classs) { this.classs = classs; }
    public String getPschool() { return pschool; }
    public void setPschool(String pschool) { this.pschool = pschool; }
    public String getAccommodation() { return accommodation; }
    public void setAccommodation(String accommodation) { this.accommodation = accommodation; }
    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }
    public String getMname() { return mname; }
    public void setMname(String mname) { this.mname = mname; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}