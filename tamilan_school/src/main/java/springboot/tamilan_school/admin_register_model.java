package springboot.tamilan_school;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_register")
public class admin_register_model {
    
    @Id
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "pass")
    private String pass;
    
    @Column(name = "dep")
    private String dep;
    
    // Constructors
    public admin_register_model() {}
    
    public admin_register_model(String id, String name, String pass, String dep) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.dep = dep;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
}