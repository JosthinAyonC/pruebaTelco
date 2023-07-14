package ec.telconet.mscomppruebamilenaorellana.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    // @GetMapping
    // //PreAutorize le da los permisos para poder realizar las peticiones, por ej si es "ROLE_ADMIN" las personas con dicho rol, pueden hacer peticiones
    // @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')") 
    // public ResponseEntity<?>  listar(@PageableDefault(page=0, size = 8) Pageable pageable){
    //     return usuarioService.listarTodos(pageable);
    // }

    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUsuarioById(@PathVariable("id") Long id ){
        return usuarioService.listarById(id);
    } 

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>  insertar(@RequestBody User usuarioBody){
        return usuarioService.insertar(usuarioBody);
    }

    // @GetMapping("/roles/{roles}")
    // @PreAuthorize("hasRole('VIGILANTE') or hasRole('ADMIN')")
    // public ResponseEntity<?> listarPorPorRoles(@PathVariable("roles") String roles){
    //     return usuarioService.listarUsuariosPorRoles(roles);
    // }
    
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody User usuarioBody){
        usuarioBody.setId(id);
        return usuarioService.actualizar(usuarioBody);
    }

    // @PutMapping("/eliminar/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    // public ResponseEntity<?> eliminar(@PathVariable Long id, @PageableDefault(page=0, size = 8) Pageable pageable){
    //     return usuarioService.eliminar(id, pageable);
    // } 
}
