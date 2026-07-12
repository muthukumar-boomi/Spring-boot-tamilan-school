package springboot.tamilan_school;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface admission_repo extends JpaRepository<admission_model, String> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO admission (id, name, date, dob, gender, classs, pschool, accommodation, fname, mname, mobile, address) " +
                   "VALUES (:id, :name, :date, :dob, :gender, :classs, :pschool, :accommodation, :fname, :mname, :mobile, :address)",
           nativeQuery = true)
    void insert(@Param("id") String id,
                @Param("name") String name,
                @Param("date") String date,
                @Param("dob") String dob,
                @Param("gender") String gender,
                @Param("classs") String classs,
                @Param("pschool") String pschool,
                @Param("accommodation") String accommodation,
                @Param("fname") String fname,
                @Param("mname") String mname,
                @Param("mobile") String mobile,
                @Param("address") String address);

    @Query(value = "SELECT * FROM admission", nativeQuery = true)
    List<admission_model> get_all();

    @Query(value = "SELECT * FROM admission WHERE id = :id", nativeQuery = true)
    admission_model get_by_id(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE admission SET name = :name, date = :date, dob = :dob, gender = :gender, " +
                   "classs = :classs, pschool = :pschool, accommodation = :accommodation, fname = :fname, " +
                   "mname = :mname, mobile = :mobile, address = :address WHERE id = :id",
           nativeQuery = true)
    void update(@Param("id") String id,
                @Param("name") String name,
                @Param("date") String date,
                @Param("dob") String dob,
                @Param("gender") String gender,
                @Param("classs") String classs,
                @Param("pschool") String pschool,
                @Param("accommodation") String accommodation,
                @Param("fname") String fname,
                @Param("mname") String mname,
                @Param("mobile") String mobile,
                @Param("address") String address);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM admission WHERE id = :id", nativeQuery = true)
    void delete(@Param("id") String id);
}