package PAT.proyectoFinal.repository;

import PAT.proyectoFinal.model.CancionModel;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.concurrent.atomic.AtomicInteger;

public interface CancionRepository extends CrudRepository<CancionModel,Integer> {

  AtomicInteger id = new AtomicInteger();

  @Query("SELECT * FROM CANCION WHERE ID=:id")
  Iterable<CancionModel> getCancionById(String id);

  //AL CREAR UNA CANCION, TAMBIEN TENGO QUE DIFERENCIAR ENTRE EL USUARIO QUE HA CREADO LA CANCION.
  @Modifying //SIEMPRE PONERLO CUANDO SE MODIFICA LA BASE DE DATOS
  @Query("INSERT INTO CANCION (id,nombre,playlist,user,artista,album,longitud) VALUES (:id,:nombre,:playlist,:usuario,:artista,:album,:longitud)")
  void createCancion(int id, String nombre, String playlist, String usuario, String artista, String album, int longitud);

  @Query("SELECT * FROM CANCION WHERE PLAYLIST=:playlist AND USER=:user")
  Iterable<CancionModel> getCancionByPlaylistUser(String playlist, String user);

  @Modifying
  @Query("DELETE FROM CANCION WHERE NAME=:nombre AND PLAYLIST=:playlist")
  void deleteCancionByNameAndPlaylist(@Param("nombre") String nombre, @Param("playlist") String playlist);
}
