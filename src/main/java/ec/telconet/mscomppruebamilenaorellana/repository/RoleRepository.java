package ec.telconet.mscomppruebamilenaorellana.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.telconet.mscomppruebamilenaorellana.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
  
    @Query("SELECT r FROM Role r ORDER BY r.id ASC")
    public List<Role> findAllRoles();

    @Query("SELECT r FROM Role r WHERE r.nombre = 'ROLE_USER'")
    public Set<Role> getRoleUser();

    @Query("SELECT r FROM Role r WHERE r.nombre = 'ROLE_ADMIN'")
    public Set<Role> getRoleAdmin();
}
