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

@Table("CANTANTES")
public class CantantesModel {
    private @Column("ID") @Id
    int id;
    private @Column("NOMBRE")
    String nombre;
    private @Column("ALBUM")
    String album;
    private @Column("USER")
    String user;
}

