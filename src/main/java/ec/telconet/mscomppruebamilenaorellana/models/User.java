package ec.telconet.mscomppruebamilenaorellana.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Email
  @NotBlank
  @Column(length = 50)
  private String username;

  @NotBlank
  @Size(max = 8)
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[#$%^&+=]).*$", message = "Debe contener al menos una mayúscula, un número y un carácter especial.")
  @Column(length = 100)
  private String password;

  @NotBlank
  @Pattern(regexp = "^[A-Z]*$", message = "Solo puede contener letras mayúsculas.")
  @Column(length = 50)
  private String firstname;

  @NotBlank
  @Pattern(regexp = "^[A-Z]*$", message = "Solo puede contener letras mayúsculas.")
  @Column(length = 50)
  private String lastname;

  @NotBlank
  @Pattern(regexp = "^[a-z]*$", message = "Solo puede contener letras minúsculas.")
  @Column(length = 100)
  private String direccion;

  @NotBlank
  @Pattern(regexp = "^\\d*$", message = "Solo puede contener números.")
  @Column(length = 20)
  private String telefono;

  @Column(length = 50)
  private String usuario_creacion;

  @Column
  private Date fecha_creacion;

  @Column(length = 50)
  private String usuario_modificacion;

  @Column
  private Date fecha_modificacion;

  @Column(length = 200)
  private String foto;

  @Column(length = 1)
  private String status;

  @OneToMany(mappedBy = "user")
  private Set<InfoUserRol> userRoles = new HashSet<>();

  public User(Long id, String username, String password, String firstname, String lastname, String direccion,
      String telefono, String usuario_creacion, Date fecha_creacion, String usuario_modificacion,
      Date fecha_modificacion, String foto, String status, Set<InfoUserRol> userRoles) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.direccion = direccion;
    this.telefono = telefono;
    this.usuario_creacion = usuario_creacion;
    this.fecha_creacion = fecha_creacion;
    this.usuario_modificacion = usuario_modificacion;
    this.fecha_modificacion = fecha_modificacion;
    this.foto = foto;
    this.status = status;
    this.userRoles = userRoles;
  }

  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
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

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Set<InfoUserRol> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<InfoUserRol> userRoles) {
    this.userRoles = userRoles;
  }

}