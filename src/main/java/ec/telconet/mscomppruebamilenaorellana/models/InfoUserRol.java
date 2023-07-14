package ec.telconet.mscomppruebamilenaorellana.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "info_user_rol")
public class InfoUserRol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_user")
  private User user;

  @ManyToOne
  @JoinColumn(name = "id_role")
  private Role role;

  @Column(length = 50)
  private String usuario_creacion;

  @Column
  private Date fecha_creacion;

  @Column(length = 50)
  private String usuario_modificacion;

  @Column
  private Date fecha_modificacion;

  @Column(length = 20)
  private String estado;
  

public InfoUserRol(Integer id, User user, Role role, String usuario_creacion, Date fecha_creacion,
        String usuario_modificacion, Date fecha_modificacion, String estado) {
    this.id = id;
    this.user = user;
    this.role = role;
    this.usuario_creacion = usuario_creacion;
    this.fecha_creacion = fecha_creacion;
    this.usuario_modificacion = usuario_modificacion;
    this.fecha_modificacion = fecha_modificacion;
    this.estado = estado;
}
public InfoUserRol(){
    
}
  // getters and setters
public Integer getId() {
    return id;
}
public void setId(Integer id) {
    this.id = id;
}
public User getUser() {
    return user;
}
public void setUser(User user) {
    this.user = user;
}
public Role getRole() {
    return role;
}
public void setRole(Role role) {
    this.role = role;
}
public String getUsuario_creacion() {
    return usuario_creacion;
}
public void setUsuario_creacion(String usuario_creacion) {
    this.usuario_creacion = usuario_creacion;
}
public Date getFecha_creacion() {
    return fecha_creacion;
}
public void setFecha_creacion(Date fecha_creacion) {
    this.fecha_creacion = fecha_creacion;
}
public String getUsuario_modificacion() {
    return usuario_modificacion;
}
public void setUsuario_modificacion(String usuario_modificacion) {
    this.usuario_modificacion = usuario_modificacion;
}
public Date getFecha_modificacion() {
    return fecha_modificacion;
}
public void setFecha_modificacion(Date fecha_modificacion) {
    this.fecha_modificacion = fecha_modificacion;
}
public String getEstado() {
    return estado;
}
public void setEstado(String estado) {
    this.estado = estado;
}

  
}