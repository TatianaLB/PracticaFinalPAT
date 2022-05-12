package PAT.proyectoFinal.repository;


import PAT.proyectoFinal.model.playlistModel;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface playlistRepository extends CrudRepository<playlistModel,String> {





  @Query("SELECT * FROM PLAYLIST WHERE NOMBRE=:id")
  Iterable<playlistModel> findPlaylistById(@Param("id") String id);

  @Modifying
  @Query("INSERT INTO PLAYLIST (NOMBRE) VALUES (:id)")
  void createPlaylistById(@Param("id") String id);

}


//DUDAS
//1. Como puedo hacer queries que cojan un input por el metodo que se lo pueda pasar mediante un HTTP request

