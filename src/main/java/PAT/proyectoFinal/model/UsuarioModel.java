package PAT.proyectoFinal.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Table("USUARIO")
public class UsuarioModel {

  private @Column("USERNAME") @Id
  String username;
  private @Column("NOMBRE")
  String nombre;
  private @Column("APELLIDO")
  String apellido;
  private @Column("EMAIL")
  String email;
  private @Column("EDAD")
  int edad;
  private @Column("PASSWORD")
  String password;

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

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UsuarioModel)) return false;

    UsuarioModel that = (UsuarioModel) o;

    if (getEdad() != that.getEdad()) return false;
    if (!getUsername().equals(that.getUsername())) return false;
    if (getNombre() != null ? !getNombre().equals(that.getNombre()) : that.getNombre() != null) return false;
    if (getApellido() != null ? !getApellido().equals(that.getApellido()) : that.getApellido() != null) return false;
    if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
    return getPassword().equals(that.getPassword());
  }

  @Override
  public int hashCode() {
    int result = getUsername().hashCode();
    result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
    result = 31 * result + (getApellido() != null ? getApellido().hashCode() : 0);
    result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
    result = 31 * result + getEdad();
    result = 31 * result + getPassword().hashCode();
    return result;
  }
}
