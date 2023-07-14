package ec.telconet.mscomppruebamilenaorellana.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ec.telconet.mscomppruebamilenaorellana.models.Role;
import ec.telconet.mscomppruebamilenaorellana.payload.response.MessageResponse;
import ec.telconet.mscomppruebamilenaorellana.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    //Get que trae todos los roles para asi guardarlo en un usuario al crear uno nuevo.
    public ResponseEntity<?> listarAllRoles() {
        return ResponseEntity.ok(roleRepository.findAllRoles());
    }

    //Get que trae los datos por id
    public ResponseEntity<?> listarById(Long id) {
        if (!roleRepository.existsById(id)) {
            return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: El usuario no ha sido encontrado"));
        }
        return ResponseEntity.ok(roleRepository.findById(id).get());
    }

    public ResponseEntity<?>  insertar(Role role) {
        if(camposUnicosYnoNulos(role).getStatusCode().is4xxClientError()){
            return camposUnicosYnoNulos(role);
        }
        roleRepository.save(role);
        return ResponseEntity.ok(role);
    }
    
    public ResponseEntity<?> actualizar(Role Role) {
        Optional<Role> optionalUsuario = roleRepository.findById(Role.getId());
        if (optionalUsuario.isEmpty()) {
          return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: El usuario no ha sido encontrado"));
        }
        Role roleEditado = optionalUsuario.get();
        copiarCamposNoNulos(Role, roleEditado);
        return ResponseEntity.ok(roleRepository.save(roleEditado));
    }
    
    public ResponseEntity<?> eliminar(Long id) {
        roleRepository.deleteById(id);
        return ResponseEntity.ok(roleRepository.findAll());
    }
    
    private void copiarCamposNoNulos(Role fuente, Role destino) {
        if (fuente.getNombre() != null) {
            destino.setNombre(fuente.getNombre());
        }
        if (fuente.getDescripcion() != null) {
            destino.setDescripcion(fuente.getDescripcion());
        }
    }

    private ResponseEntity<?> camposUnicosYnoNulos(Role role) {
    
        if (role.getNombre() == null || role.getDescripcion() == null) 
        {
            return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Campos vacios!"));
        }
        return ResponseEntity.ok(role);
    
    }
}
