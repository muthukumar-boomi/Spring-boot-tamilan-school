package springboot.tamilan_school;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface student_record_repo extends JpaRepository<student_record_model, String> {
    
    // ========== INSERT ==========
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO student_record (id, name, gender, email, dob, blood, classs, section, grade, pname, mobile, address) " +
                   "VALUES (:id, :name, :gender, :email, :dob, :blood, :classs, :section, :grade, :pname, :mobile, :address)", 
           nativeQuery = true)
    void insert(
        @Param("id") String id,
        @Param("name") String name,
        @Param("gender") String gender,
        @Param("email") String email,
        @Param("dob") String dob,
        @Param("blood") String blood,
        @Param("classs") String classs,
        @Param("section") String section,
        @Param("grade") String grade,
        @Param("pname") String pname,
        @Param("mobile") String mobile,
        @Param("address") String address);

    // ========== GET ALL STUDENTS ==========
    @Query(value = "SELECT * FROM student_record", nativeQuery = true)
    List<student_record_model> get_all();
    
    // ========== GET BY ID ==========
    @Query(value = "SELECT * FROM student_record WHERE id = :id", nativeQuery = true)
    student_record_model get_by_id(@Param("id") String id);
    
    // ========== UPDATE ==========
    @Transactional
    @Modifying
    @Query(value = "UPDATE student_record SET name = :name, gender = :gender, email = :email, " +
                   "dob = :dob, blood = :blood, classs = :classs, section = :section, " +
                   "grade = :grade, pname = :pname, mobile = :mobile, address = :address " +
                   "WHERE id = :id", 
           nativeQuery = true)
    void update(
        @Param("id") String id,
        @Param("name") String name,
        @Param("gender") String gender,
        @Param("email") String email,
        @Param("dob") String dob,
        @Param("blood") String blood,
        @Param("classs") String classs,
        @Param("section") String section,
        @Param("grade") String grade,
        @Param("pname") String pname,
        @Param("mobile") String mobile,
        @Param("address") String address);
    
    // ========== DELETE ==========
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM student_record WHERE id = :id", nativeQuery = true)
    void delete(@Param("id") String id);
    
    // ========== SEARCH ==========
    @Query(value = "SELECT * FROM student_record WHERE " +
                   "(:id IS NULL OR id LIKE CONCAT('%', :id, '%')) AND " +
                   "(:name IS NULL OR name LIKE CONCAT('%', :name, '%')) AND " +
                   "(:classs IS NULL OR classs LIKE CONCAT('%', :classs, '%')) AND " +
                   "(:section IS NULL OR section LIKE CONCAT('%', :section, '%')) AND " +
                   "(:grade IS NULL OR grade LIKE CONCAT('%', :grade, '%')) AND " +
                   "(:blood IS NULL OR blood LIKE CONCAT('%', :blood, '%'))", 
           nativeQuery = true)
    List<student_record_model> search(
        @Param("id") String id,
        @Param("name") String name,
        @Param("classs") String classs,
        @Param("section") String section,
        @Param("grade") String grade,
        @Param("blood") String blood);
    
    // ========== GET BY CLASS & SECTION ==========
    @Query(value = "SELECT * FROM student_record WHERE classs = :classs AND section = :section", 
           nativeQuery = true)
    List<student_record_model> get_by_class_section(
        @Param("classs") String classs,
        @Param("section") String section);
}