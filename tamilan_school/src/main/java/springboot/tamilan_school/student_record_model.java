package springboot.tamilan_school;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_record")
public class student_record_model {
    
    @Id
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "gender")
    private String gender;
    

    @Column(name = "email")
    private String email;
    
    @Column(name = "dob")
    private String dob;
    

    @Column(name = "blood")
    private String blood;
    

    @Column(name = "classs")
    private String classs;
    

    @Column(name = "section")
    private String section;
    
    @Column(name = "grade")
    private String grade;
    

    @Column(name = "pname")
    private String pname;
    

    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "address")
    private String address;
    
    
    
    // Constructors
    public student_record_model() {}
    
    public student_record_model(String id, String name, String gender,String email, String dob,
    		String blood,String classs, String section, String grade ,String pname, String mobile, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.blood = blood;
        this.classs = classs;
        this.section = section;
        this.grade = grade;
        this.pname = pname;
        this.mobile = mobile;
        this.address = address;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getClasss() {
		return classs;
	}

	public void setClasss(String classs) {
		this.classs = classs;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
        
    
    
}
