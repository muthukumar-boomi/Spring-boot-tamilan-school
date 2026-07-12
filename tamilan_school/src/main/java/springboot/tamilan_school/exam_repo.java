package springboot.tamilan_school;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface exam_repo extends JpaRepository<exam_model, String> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO exam (id, name, etype, subject, date, time, section, tmark, mark, grade, status) " +
                   "VALUES (:id, :name, :etype, :subject, :date, :time, :section, :tmark, :mark, :grade, :status)",
           nativeQuery = true)
    void insert(@Param("id") String id,
                @Param("name") String name,
                @Param("etype") String etype,
                @Param("subject") String subject,
                @Param("date") String date,
                @Param("time") String time,
                @Param("section") String section,
                @Param("tmark") String tmark,
                @Param("mark") String mark,
                @Param("grade") String grade,
                @Param("status") String status);

    @Query(value = "SELECT * FROM exam", nativeQuery = true)
    List<exam_model> get_all();

    @Query(value = "SELECT * FROM exam WHERE id = :id", nativeQuery = true)
    exam_model get_by_id(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE exam SET name = :name, etype = :etype, subject = :subject, date = :date, " +
                   "time = :time, section = :section, tmark = :tmark, mark = :mark, grade = :grade, status = :status WHERE id = :id",
           nativeQuery = true)
    void update(@Param("id") String id,
                @Param("name") String name,
                @Param("etype") String etype,
                @Param("subject") String subject,
                @Param("date") String date,
                @Param("time") String time,
                @Param("section") String section,
                @Param("tmark") String tmark,
                @Param("mark") String mark,
                @Param("grade") String grade,
                @Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM exam WHERE id = :id", nativeQuery = true)
    void delete(@Param("id") String id);
}