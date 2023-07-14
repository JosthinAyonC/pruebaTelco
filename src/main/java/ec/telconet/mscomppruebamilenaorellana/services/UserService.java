package ec.telconet.mscomppruebamilenaorellana.services;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ec.telconet.mscomppruebamilenaorellana.models.InfoUserRol;
import ec.telconet.mscomppruebamilenaorellana.models.Role;
import ec.telconet.mscomppruebamilenaorellana.models.User;
import ec.telconet.mscomppruebamilenaorellana.payload.response.MessageResponse;
import ec.telconet.mscomppruebamilenaorellana.repository.InfoUserRoleRepository;
import ec.telconet.mscomppruebamilenaorellana.repository.RoleRepository;
import ec.telconet.mscomppruebamilenaorellana.repository.UserRepository;



@Service
public class UserService {
    @Autowired
    private UserRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private InfoUserRoleRepository infoUserRoleRepository;
    @Autowired
    PasswordEncoder encoder;

    //Post que se encarga de verificar que no existan datos duplicados y nulos, y posterior a eso inserta el dato.
    public ResponseEntity<?>  insertar(User userNew) {
        if(camposUnicosYnoNulos(userNew).getStatusCode().is4xxClientError()){
            return camposUnicosYnoNulos(userNew);
        }
        userNew.setPassword(encoder.encode(userNew.getPassword()));
        usuarioRepository.save(userNew);
        return ResponseEntity.ok(userNew);
    }

    //Put que se encarga de actualizar los campos de los datos, solo los que vengan en el requestBody.
    public ResponseEntity<?> actualizar(User user) {
        Optional<User> optionalUsuario = usuarioRepository.findById(user.getId());
        if (optionalUsuario.isEmpty()) {
          return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: El usuario no ha sido encontrado"));
        }
        User usuarioEditado = optionalUsuario.get();
        copiarCamposNoNulos(user, usuarioEditado);
        return ResponseEntity.ok(usuarioRepository.save(usuarioEditado));
    }

    // //Get que trae los datos paginagos
    // public ResponseEntity<?> listarTodos(Pageable pageable) {
    //     Page<User> usersPage = usuarioRepository.findByEstado(pageable);
    //     return ResponseEntity.ok(usersPage);
    // }

    
    //Get que trae los datos por id
    public ResponseEntity<?> listarById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: El usuario no ha sido encontrado"));
        }
        return ResponseEntity.ok(usuarioRepository.findById(id).get());
    }

    // //Put que elimnado logico del dato al recibir el id por pathVariable
    public ResponseEntity<?> eliminar(Long id, Pageable pageable) {
        usuarioRepository.deleteById(id);
        Page<User> usersPage = usuarioRepository.findByEstado(pageable);
        return ResponseEntity.ok(usersPage);
    }
    
    // //Get que devuelve usuarios que contengan el rol que se pasa como string en un pathVariable
    // public ResponseEntity<?> listarUsuariosPorRoles(String roles) {
    //     List<User> usuarios = usuarioRepository.findByRoles(roles);
    //     if (usuarios.isEmpty()) {
    //         return ResponseEntity
    //         .badRequest()
    //         .body(new MessageResponse("Error: No se encontraron usuarios con el rol: " + roles));
    //     }
    //     return ResponseEntity.ok(usuarios);
    // }

    // //en la ruta /api/auth/signup va a guardar nuevo usuario
    @Transactional
    public ResponseEntity<?> registrar(@Valid User signUpRequest) {
        if(camposUnicosYnoNulos(signUpRequest).getStatusCode().is4xxClientError()){
            return camposUnicosYnoNulos(signUpRequest);
        }
    
        signUpRequest.setStatus("A");
        signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
        
        // Guarda el usuario primero
        User savedUser = usuarioRepository.save(signUpRequest);
    
        // Obtén el rol de usuario
        Set<Role> role = roleRepository.getRoleUser();
        Role rol = role.iterator().next();
        
        InfoUserRol infoUserRol = new InfoUserRol();
        infoUserRol.setUser(savedUser); // Usa el usuario guardado
        infoUserRol.setRole(rol);
        infoUserRol.setEstado("A");
        infoUserRol.setFecha_creacion(new Date());
        infoUserRoleRepository.save(infoUserRol);
        
        return ResponseEntity.ok(new MessageResponse("Usuario registrado satisfactoriamente!"));
    }

     public ResponseEntity<?> listarTodos(Pageable pageable) {
        Page<User> usersPage = usuarioRepository.findByEstado(pageable);
        return ResponseEntity.ok(usersPage);
    }

    //Metodo para copiar campos no nulos
    private void copiarCamposNoNulos(User fuente, User destino) {
        if (fuente.getFirstname() != null) {
            destino.setFirstname(fuente.getFirstname());
        }
        if (fuente.getLastname() != null) {
            destino.setLastname(fuente.getLastname());
        }
        
        if (fuente.getPassword() != null) {
            destino.setPassword(encoder.encode(fuente.getPassword()));
        }

        if (fuente.getStatus() != null) {
            destino.setStatus(fuente.getStatus());
        }
    }

    // Metodo para verificar email, usuario existente y campos vacios
    private ResponseEntity<?> camposUnicosYnoNulos(User user) {
        if (usuarioRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: correo utilizado anteriormente!, prueba con otro nombre de correo."));
        }
        if (
            // user.getUsername() == null || user.getEmail() == null || 
        user.getLastname() == null || user.getFirstname() == null || 
        user.getPassword() == null) {
            return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Campos vacios!"));
        }
        return ResponseEntity.ok(user);
    }

    @Transactional
    public void crearUsuarioAdmin() {
        User user = new User();
        user.setFirstname("ADMINISTRADOR");
        user.setLastname("ADMIN");
        user.setUsername("admin@admin.com");
        user.setPassword(encoder.encode("admin@1"));
        user.setDireccion("admin");
        user.setTelefono("09652371263");
        user.setFecha_creacion(new Date());
        user.setStatus("A");
        User savedUser = usuarioRepository.save(user);

        Set<Role> role = roleRepository.getRoleAdmin();
        Role rol = role.iterator().next();
        
        InfoUserRol infoUserRol = new InfoUserRol();
        infoUserRol.setUser(savedUser);
        infoUserRol.setRole(rol);
        infoUserRol.setEstado("A");
        infoUserRol.setFecha_creacion(new Date());
        infoUserRoleRepository.save(infoUserRol);
    }

}
