package springboot.tamilan_school;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface timetable_repo extends JpaRepository<timetable_model, String> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO timetable (id, classs, section, subject, staff, day, stime, etime) " +
                   "VALUES (:id, :classs, :section, :subject, :staff, :day, :stime, :etime)",
           nativeQuery = true)
    void insert(@Param("id") String id,
                @Param("classs") String classs,
                @Param("section") String section,
                @Param("subject") String subject,
                @Param("staff") String staff,
                @Param("day") String day,
                @Param("stime") String stime,
                @Param("etime") String etime);

    @Query(value = "SELECT * FROM timetable ORDER BY classs, section, day, stime", nativeQuery = true)
    List<timetable_model> get_all();

    @Query(value = "SELECT * FROM timetable WHERE id = :id", nativeQuery = true)
    timetable_model get_by_id(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE timetable SET classs = :classs, section = :section, subject = :subject, " +
                   "staff = :staff, day = :day, stime = :stime, etime = :etime WHERE id = :id",
           nativeQuery = true)
    void update(@Param("id") String id,
                @Param("classs") String classs,
                @Param("section") String section,
                @Param("subject") String subject,
                @Param("staff") String staff,
                @Param("day") String day,
                @Param("stime") String stime,
                @Param("etime") String etime);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM timetable WHERE id = :id", nativeQuery = true)
    void delete(@Param("id") String id);
}