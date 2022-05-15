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
}