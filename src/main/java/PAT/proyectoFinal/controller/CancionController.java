package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.model.CancionModel;
import PAT.proyectoFinal.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class CancionController {

  @Autowired
  CancionService cancionService;


  @GetMapping("/canciones")
  public ResponseEntity<Iterable<CancionModel>> getCanciones(){
    return ResponseEntity.ok().body(cancionService.getCancionesService());
  }

  @GetMapping("/cancion/{id}")
  public ResponseEntity<Iterable<CancionModel>> getCancionById(@PathVariable String id){


    //return ResponseEntity.ok().body(id);

    return ResponseEntity.ok().body(cancionService.getCancionByIdService(id));
  }

  @GetMapping("/cancion/delete/{name}")
  @ResponseBody
  public ResponseEntity<Void> deleteCancionById(
          @PathVariable String name,
          @RequestParam(value="playlist",required=true) String playlist
          ){
    cancionService.deleteCancionByNameAndPlaylistService(name, playlist); //CAMBIAR PARA TENER EN CUENTA EL USUARIO
    return ResponseEntity.ok().build();
  }

  @PostMapping("/cancion/create")
  public ResponseEntity<String> createCancionById(
          @RequestBody CancionModel cancion,
          BindingResult bindingResult){

    if(bindingResult.hasErrors()){
      return new ResponseEntity<String>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
    }else{
      cancionService.createCancionService(cancion);
      return new ResponseEntity<String>("{\"result\" : \"OK\"}", HttpStatus.OK);
    }

  }


  @GetMapping("/canciones/playlist/{id}")
  public ResponseEntity<Iterable<CancionModel>> getCancionesByPlaylist(
          @PathVariable String id,
          @RequestParam(value="accessToken",required=true) String user){

    return ResponseEntity.ok().body(cancionService.getCancionesByPlaylistUserService(id,user));
  }

}
