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
public class cancionModel {
  private @Column("ID") @Id
  int id;
  private @Column("NOMBRE")
  String nombre;
  private @Column("PLAYLIST")
  String playlist;
  private @Column("ARTISTA")
  String artista;
  private @Column("ALBUM")
  String album;
  private @Column("LONGITUD")
  int longitud;

}