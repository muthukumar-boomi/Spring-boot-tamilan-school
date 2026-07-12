package springboot.tamilan_school;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface attendance_repo extends JpaRepository<attendance_model, String> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO attendance (id, name, date, gender, classs, section, status) " +
                   "VALUES (:id, :name, :date, :gender, :classs, :section, :status)",
           nativeQuery = true)
    void insert(@Param("id") String id,
                @Param("name") String name,
                @Param("date") String date,
                @Param("gender") String gender,
                @Param("classs") String classs,
                @Param("section") String section,
                @Param("status") String status);

    @Query(value = "SELECT * FROM attendance ORDER BY date DESC, id ASC", nativeQuery = true)
    List<attendance_model> get_all();

    @Query(value = "SELECT * FROM attendance WHERE id = :id", nativeQuery = true)
    attendance_model get_by_id(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE attendance SET name = :name, date = :date, gender = :gender, " +
                   "classs = :classs, section = :section, status = :status WHERE id = :id",
           nativeQuery = true)
    void update(@Param("id") String id,
                @Param("name") String name,
                @Param("date") String date,
                @Param("gender") String gender,
                @Param("classs") String classs,
                @Param("section") String section,
                @Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM attendance WHERE id = :id", nativeQuery = true)
    void delete(@Param("id") String id);
}