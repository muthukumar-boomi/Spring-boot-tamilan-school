package springboot.tamilan_school;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface staff_record_repo extends JpaRepository<staff_record_model, String> {

    // INSERT
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO staff_record (id, name, gender, email, mobile, dep, designation, subject, exp) " +
                   "VALUES (:id, :name, :gender, :email, :mobile, :dep, :designation, :subject, :exp)",
           nativeQuery = true)
    void insert(@Param("id") String id,
                @Param("name") String name,
                @Param("gender") String gender,
                @Param("email") String email,
                @Param("mobile") String mobile,
                @Param("dep") String dep,
                @Param("designation") String designation,
                @Param("subject") String subject,
                @Param("exp") String exp);

    // GET ALL
    @Query(value = "SELECT * FROM staff_record", nativeQuery = true)
    List<staff_record_model> get_all();

    // GET BY ID
    @Query(value = "SELECT * FROM staff_record WHERE id = :id", nativeQuery = true)
    staff_record_model get_by_id(@Param("id") String id);

    // UPDATE
    @Transactional
    @Modifying
    @Query(value = "UPDATE staff_record SET name = :name, gender = :gender, email = :email, " +
                   "mobile = :mobile, dep = :dep, designation = :designation, subject = :subject, exp = :exp WHERE id = :id",
           nativeQuery = true)
    void update(@Param("id") String id,
                @Param("name") String name,
                @Param("gender") String gender,
                @Param("email") String email,
                @Param("mobile") String mobile,
                @Param("dep") String dep,
                @Param("designation") String designation,
                @Param("subject") String subject,
                @Param("exp") String exp);

    // DELETE
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM staff_record WHERE id = :id", nativeQuery = true)
    void delete(@Param("id") String id);
}