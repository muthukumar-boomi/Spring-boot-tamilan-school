package springboot.tamilan_school;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public interface fees_repo extends JpaRepository<fees_model, String> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO fees (id, name, ftype, ddate, rs, pdate, pmode, pamount, status) " +
                   "VALUES (:id, :name, :ftype, :ddate, :rs, :pdate, :pmode, :pamount, :status)", 
           nativeQuery = true)
    void insert(@Param("id") String id,
                @Param("name") String name,
                @Param("ftype") String ftype,
                @Param("ddate") String ddate,
                @Param("rs") String rs,
                @Param("pdate") String pdate,
                @Param("pmode") String pmode,
                @Param("pamount") String pamount,
                @Param("status") String status);

    @Query(value = "SELECT * FROM fees", nativeQuery = true)
    List<fees_model> get_all();

    @Query(value = "SELECT * FROM fees WHERE id = :id", nativeQuery = true)
    fees_model get_by_id(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE fees SET name = :name, ftype = :ftype, ddate = :ddate, rs = :rs, " +
                   "pdate = :pdate, pmode = :pmode, pamount = :pamount, status = :status WHERE id = :id", 
           nativeQuery = true)
    void update(@Param("id") String id,
                @Param("name") String name,
                @Param("ftype") String ftype,
                @Param("ddate") String ddate,
                @Param("rs") String rs,
                @Param("pdate") String pdate,
                @Param("pmode") String pmode,
                @Param("pamount") String pamount,
                @Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM fees WHERE id = :id", nativeQuery = true)
    void delete(@Param("id") String id);
}