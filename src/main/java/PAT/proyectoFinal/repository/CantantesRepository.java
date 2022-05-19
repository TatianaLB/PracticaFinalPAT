package PAT.proyectoFinal.repository;

import PAT.proyectoFinal.model.CantantesModel;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.concurrent.atomic.AtomicInteger;

public interface CantantesRepository extends CrudRepository<CantantesModel,Integer> {
    AtomicInteger id = new AtomicInteger();

    @Modifying
    @Query("INSERT INTO CANTANTES (ID, NOMBRE,ALBUM, USER) VALUES (:id, :nombre, :album, :user)")
    void createCantanteFav(int id, @Param("nombre") String nombre,@Param("album") String album, @Param("user") String user);

    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM CANTANTES WHERE ID=:id AND USER=:user) THEN TRUE ELSE FALSE END AS bool")
    String trueOrFalsePlaylistService(@Param("id") int id, @Param("user") String user);

    @Query("SELECT * FROM CANTANTES WHERE USER=:user")
    Iterable<CantantesModel> findCantantesByUser(@Param("user") String user);
}
