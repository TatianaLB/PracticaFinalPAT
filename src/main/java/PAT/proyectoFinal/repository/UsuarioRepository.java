package PAT.proyectoFinal.repository;


import PAT.proyectoFinal.model.UsuarioModel;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepository extends CrudRepository<UsuarioModel,String> {



  @Query("SELECT * FROM USUARIO WHERE USERNAME=:id")
  Iterable<UsuarioModel> getUsuarioById(@Param("id") String id);

  @Modifying
  @Query("INSERT INTO USUARIO (username,nombre,apellido,email,edad,password) " +
          "VALUES (:username,:nombre,:apellido,:email,:edad,:password)")
  void crearUsuario(String username,
                    String nombre,
                    String apellido,
                    String email,
                    int edad,
                    String password);

  @Query("SELECT PASSWORD FROM USUARIO WHERE USERNAME=:id")
  String getPasswordFromUsername(@Param("id") String id);
}
