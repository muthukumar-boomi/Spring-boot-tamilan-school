package springboot.tamilan_school;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

public interface admin_register_repo extends JpaRepository<admin_register_model, String> {
    
    // Insert admin
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_admin_register_insert :id, :name, :pass, :dep", nativeQuery = true)
    void insert(
        @Param("id") String id,
        @Param("name") String name,
        @Param("pass") String pass,
        @Param("dep") String dep);
    
    // Get all admins
    @Query(value = "EXEC sp_admin_register_get_all", nativeQuery = true)
    List<admin_register_model> get_all();
    
    // Get admin by ID
    @Query(value = "EXEC sp_admin_register_get_by_id :id", nativeQuery = true)
    admin_register_model get_by_id(@Param("id") String id);
    
    // Get admin by name
    @Query(value = "EXEC sp_admin_register_get_by_name :name", nativeQuery = true)
    List<admin_register_model> get_by_name(@Param("name") String name);
    
    // Get admin by department
    @Query(value = "EXEC sp_admin_register_get_by_dep :dep", nativeQuery = true)
    List<admin_register_model> get_by_dep(@Param("dep") String dep);
    
    // Update admin
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_admin_register_update :id, :name, :pass, :dep", nativeQuery = true)
    void update(
        @Param("id") String id,
        @Param("name") String name,
        @Param("pass") String pass,
        @Param("dep") String dep);
    
    // Delete admin
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_admin_register_delete :id", nativeQuery = true)
    void delete(@Param("id") String id);
    
    // Check if admin exists
    @Query(value = "EXEC sp_admin_register_exists :id", nativeQuery = true)
    Integer exists_by_id(@Param("id") String id);
    
    // Admin login verification
    @Query(value = "EXEC sp_admin_register_login :id, :pass", nativeQuery = true)
    admin_register_model login(
        @Param("id") String id,
        @Param("pass") String pass);
    
    // Update password only
    @Transactional
    @Modifying
    @Query(value = "EXEC sp_admin_register_update_password :id, :pass", nativeQuery = true)
    void update_password(
        @Param("id") String id,
        @Param("pass") String pass);
    
    // Search admins
    @Query(value = "EXEC sp_admin_register_search :id, :name, :dep", nativeQuery = true)
    List<admin_register_model> search(
        @Param("id") String id,
        @Param("name") String name,
        @Param("dep") String dep);
}