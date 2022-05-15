package PAT.proyectoFinal.service.Impl;

import PAT.proyectoFinal.model.UsuarioModel;
import PAT.proyectoFinal.repository.UsuarioRepository;
import PAT.proyectoFinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;


  @Override
  public Iterable<UsuarioModel> getUsuariosService(){
    return usuarioRepository.findAll();
  }

  @Override
  public Iterable<UsuarioModel> getUsuarioByIdService(String id){

    return usuarioRepository.getUsuarioById(id);
  }

  @Override
  public void deleteUsuarioByIdService(String id){
    usuarioRepository.deleteById(id);
  }

  @Override
  public String compararYCrearUsuarioService(UsuarioModel usuario){

    String username = usuario.getUsername();
    boolean usuarioExists = usuarioRepository.existsById(username);
    if(usuarioExists){
      return "User Already Exists.";
    }else{
      String nombre = usuario.getNombre();
      String apellido = usuario.getApellido();
      String email = usuario.getEmail();
      int edad = usuario.getEdad();
      String password = usuario.getPassword();
      usuarioRepository.crearUsuario(username,nombre,apellido,email,edad,password);
      return "OK";
    }

  }


}
