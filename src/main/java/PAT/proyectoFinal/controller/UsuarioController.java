package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.exception.UserAlreadyExistsException;
import PAT.proyectoFinal.exception.UserDoesntExistLogInException;
import PAT.proyectoFinal.exception.WrongPasswordLogInException;
import PAT.proyectoFinal.model.UsuarioModel;
import PAT.proyectoFinal.service.LoginService;
import PAT.proyectoFinal.service.LoginServiceResult;
import PAT.proyectoFinal.service.UsuarioService;
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
  UsuarioService usuarioService;

  @Autowired
  private LoginService loginService;

  @GetMapping("/usuarios")
  public ResponseEntity<Iterable<UsuarioModel>> getUsuarios() {
    return ResponseEntity.ok().body(usuarioService.getUsuariosService());
  }


  @GetMapping("/usuario/{id}")
  public ResponseEntity<Iterable<UsuarioModel>> getUsuarioById(@PathVariable String id) {


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
          @RequestBody UsuarioModel usuario,
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

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> compararUsuario(
          @RequestBody UsuarioModel usuario,
          BindingResult bindingResult){

    if (bindingResult.hasErrors()) {
      LoginResponse loginResponse = new LoginResponse("KO");
      return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
    }

      LoginServiceResult result = loginService.LogInUsuarioService(usuario);

      if (result.isFlag()) {//Si el login ha sido correcto y se ha devuelto un access token.
        LoginResponse loginResponse = new LoginResponse("OK", result.getAccessToken());
        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
      } else if (result.equals("Not Found")) {
        throw new UserDoesntExistLogInException();
      } else if (result.equals("Wrong Password")) {
        throw new WrongPasswordLogInException();
      }
      LoginResponse loginResponse = new LoginResponse("KO");
      return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);




  }


}


