package PAT.proyectoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@NoArgsConstructor
//@AllArgsConstructor
@Table("CANCION")
public class CancionModel {
  @Id
  @Column("ID")
  private int id;

  @Column("NOMBRE")
  private String nombre;

  @Column("PLAYLIST")
  private String playlist;

  @Column("ARTISTA")
  private String artista;

  @Column("ALBUM")
  private String album;

  @Column("LONGITUD")
  private int longitud;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CancionModel)) return false;

    CancionModel that = (CancionModel) o;

    return getId() == that.getId();
  }

  @Override
  public int hashCode() {
    return getId();
  }


  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getPlaylist() {
    return playlist;

  }

  public String getArtista() {
    return artista;
  }

  public String getAlbum() {
    return album;
  }

  public int getLongitud() {
    return longitud;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setPlaylist(String playlist) {
    this.playlist = playlist;
  }

  public void setArtista(String artista) {
    this.artista = artista;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public void setLongitud(int longitud) {
    this.longitud = longitud;
  }

}