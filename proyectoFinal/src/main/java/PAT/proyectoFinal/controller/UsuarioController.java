package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.exception.UserAlreadyExistsException;
import PAT.proyectoFinal.exception.UserDoesntExistLogInException;
import PAT.proyectoFinal.exception.WrongPasswordLogInException;
import PAT.proyectoFinal.model.usuarioModel;
import PAT.proyectoFinal.service.usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class UsuarioController {

  @Autowired
  usuarioService usuarioService;

  @GetMapping("/usuarios")
  public ResponseEntity<Iterable<usuarioModel>> getUsuarios() {
    return ResponseEntity.ok().body(usuarioService.getUsuariosService());
  }


  @GetMapping("/usuario/{id}")
  public ResponseEntity<Iterable<usuarioModel>> getUsuarioById(@PathVariable String id) {


    //return ResponseEntity.ok().body(id);

    return ResponseEntity.ok().body(usuarioService.getUsuarioByIdService(id));
  }

  @GetMapping("/usuario/delete/{id}")
  public ResponseEntity<Void> deleteUsuarioById(@PathVariable String id) {
    usuarioService.deleteUsuarioByIdService(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/signup")
  public ResponseEntity<String> crearYCompararUsuario(
          @RequestBody usuarioModel usuario,
          BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
    }else{
      String comparar = usuarioService.compararYCrearUsuarioService(usuario);

      if(comparar == "OK"){
        return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
      }else{
        throw new UserAlreadyExistsException();
      }
    }
  }

  @PostMapping("/login") //HACE FALTA EL CODIGO DE VERIFICACION Y TO ESO DEL NUEVO TEMA.
  public ResponseEntity<String> compararUsuario(
          @RequestBody usuarioModel usuario,
          BindingResult bindingResult){

    if (bindingResult.hasErrors()) {
      return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
    }

    String result = usuarioService.LogInUsuarioService(usuario);

    if(result.equals("LogIn")){
      return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
    }else if(result.equals("Not Found")){
      throw new UserDoesntExistLogInException();
    }else if(result.equals("Wrong Password")){
      throw new WrongPasswordLogInException();
    }

    return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);



  }


}


