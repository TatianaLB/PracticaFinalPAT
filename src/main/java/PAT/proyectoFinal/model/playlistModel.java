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

@Table("PLAYLIST")
public class playlistModel {
  private @Column("ID") @Id
  int id;
  private @Column("NOMBRE")
  String nombre;
  private @Column("USER")
  String user;
}
