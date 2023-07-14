package ec.telconet.mscomppruebamilenaorellana.controllers;

import java.util.Map;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.telconet.mscomppruebamilenaorellana.models.User;
import ec.telconet.mscomppruebamilenaorellana.services.UserService;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService usuarioService;

    // PreAutorize le da los permisos para poder realizar las peticiones, por ej si
    // es "ROLE_ADMIN" las personas con dicho rol, pueden hacer peticiones
    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> listar(@PageableDefault(page = 0, size = 8) Pageable pageable) {
        return usuarioService.listarTodos(pageable);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUsuarioById(@PathVariable("id") Long id) {
        return usuarioService.listarById(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> insertar(@RequestBody User usuarioBody) {
        return usuarioService.insertar(usuarioBody);
    }
    
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody User usuarioBody) {
        usuarioBody.setId(id);
        return usuarioService.actualizar(usuarioBody);
    }
    
    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @PageableDefault(page = 0, size = 8) Pageable pageable) {
        return usuarioService.eliminar(id, pageable);
    }
    // @PostMapping
    // public List<User> insertarUser(@RequestBody User persona) {
    // return userRepository.prPersona("I",
    // 0,
    // persona.getFirstname(),
    // persona.getLastname(),
    // persona.getTelefono(),
    // persona.getDireccion(),
    // persona.getUsername(),
    // persona.getPassword(),
    // null,
    // null);
    // }
}
