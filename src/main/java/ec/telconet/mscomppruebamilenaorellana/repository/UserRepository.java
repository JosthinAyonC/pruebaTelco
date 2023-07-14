package ec.telconet.mscomppruebamilenaorellana.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ec.telconet.mscomppruebamilenaorellana.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  // Boolean existsByEmail(String email);

  // @Modifying
  // @Query("UPDATE User u SET u.status = null WHERE u.id = ?1")
  // public void deleteById(Long id);

  // @Query("SELECT u FROM User u WHERE u.status != null ORDER BY u.id DESC")
  // Page<User> findByEstado(Pageable pageable);

  // @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
  //   List<User> findByRoles(@Param("roleName") String roleName);
}
