package ec.telconet.mscomppruebamilenaorellana.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import ec.telconet.mscomppruebamilenaorellana.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  // @Procedure(procedureName = "pr_persona")
  //   List<User> prPersona(
  //       @Param("i_accion") String accion,
  //       @Param("i_id_persona") Integer idPersona,
  //       @Param("i_nombre") String firstname,
  //       @Param("i_apellido") String lastname,
  //       @Param("i_telefono") String telefono,
  //       @Param("i_direccion") String direccion,
  //       @Param("i_username") String username,
  //       @Param("i_password") String password,
  //       @Param("i_offset") Integer offset,
  //       @Param("i_limit") Integer limit
  //   );


  @Query("SELECT u FROM User u ORDER BY u.id DESC")
  Page<User> findByEstado(Pageable pageable);

  // @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
  //   List<User> findByRoles(@Param("roleName") String roleName);
}
